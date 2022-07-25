import Combine
import SwiftUI
import shared

/// Create a Combine publisher from the supplied `FlowAdapter`. Use this in contexts where more transformation will be
/// done on the Swift side before the value is bound to UI
func createPublisher<T>(_ flowAdapter: FlowAdapter<T>) -> AnyPublisher<T, KotlinError> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, KotlinError>>> {
        let subject = PassthroughSubject<T, KotlinError>()
        let canceller = flowAdapter.subscribe(
            onEach: { item in subject.send(item) },
            onComplete: { subject.send(completion: .finished) },
            onThrow: { error in subject.send(completion: .failure(KotlinError(error))) }
        )
        return subject.handleEvents(receiveCancel: { canceller.cancel() })
    }.eraseToAnyPublisher()
}

/// Prepare the supplied `FlowAdapter` to be bound to UI. The `onEach` callback will be called from `DispatchQueue.main`
/// on every new emission.
///
/// Note that this calls `assertNoFailure()` internally so you should handle errors upstream to avoid crashes.
func doPublish<T>(_ flowAdapter: FlowAdapter<T>, onEach: @escaping (T) -> Void) -> Cancellable {
    return createPublisher(flowAdapter)
        .assertNoFailure()
        .compactMap { $0 }
        .receive(on: DispatchQueue.main)
        .sink { onEach($0) }
}

/// Wraps a `KotlinThrowable` in a `LocalizedError` which can be used as  a Combine error type
class KotlinError: LocalizedError {
    let throwable: KotlinThrowable

    init(_ throwable: KotlinThrowable) {
        self.throwable = throwable
    }
    var errorDescription: String? {
        throwable.message
    }
}

class BallastObservable<Inputs: AnyObject, Events: AnyObject, State: AnyObject>: ObservableObject {
    private var cancellables = [AnyCancellable]()
    @Published public private(set) var vmState: State

    private var viewModelFactory: ()->IosViewModel<Inputs, Events, State>
    private var eventHandlerFactory: (()->EventHandler)?

    private var viewModel: IosViewModel<Inputs, Events, State>?

    init(
        viewModelFactory: @escaping ()->IosViewModel<Inputs, Events, State>,
        eventHandlerFactory: (()->EventHandler)? = nil
    ) {
        self.viewModelFactory = viewModelFactory
        self.eventHandlerFactory = eventHandlerFactory
        self.vmState = self.viewModelFactory().initialState
    }

    func activate() {
        let viewModel = self.viewModelFactory()
        self.vmState = viewModel.initialState

        doPublish(viewModel.stateCallbacks) { [weak self] vmState in
            self?.vmState = vmState
        }.store(in: &cancellables)

        if let eventHandler = self.eventHandlerFactory?() {
            let eventHandlerCanceller = viewModel.attachEventHandler(handler: eventHandler)
            cancellables.append(AnyCancellable { eventHandlerCanceller.cancel() })
        }

        self.viewModel = viewModel
    }

    func deactivate() {
        cancellables.forEach { $0.cancel() }
        cancellables.removeAll()

        viewModel?.close()
        viewModel = nil
    }

    func postInput(_ input: Inputs) {
        self.viewModel?.trySend(element: input)
    }
}

extension View {
    func withViewModel<Inputs: AnyObject, Events: AnyObject, State: AnyObject>(
        _ observableModel: BallastObservable<Inputs, Events, State>,
        _ onInitialize: @escaping ()->Void = { }
    ) -> some View {
        return self.onAppear(perform: {
                observableModel.activate()
                onInitialize()
            })
            .onDisappear(perform: {
                observableModel.deactivate()
            })
    }
}
