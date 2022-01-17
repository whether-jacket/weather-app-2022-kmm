import SwiftUI
import shared

private let log = koin.loggerWithTag(tag: "ObservableWeatherModel")

class ObservableWeatherModel: ObservableObject {
        private var viewModel: ViewModel?

        @Published
        var loading = false

        @Published
        var weatherReport: WeatherReport?

        @Published
        var error: String?

        func activate() {
            viewModel = ViewModel { [weak self] dataState in
                self?.loading = dataState.loading
                self?.weatherReport = dataState.data
                self?.error = dataState.exception

                if let weatherReport = dataState.data {
                    log.d(message: {"View updating with Weather Stats:\(weatherReport.cityTitle)"})
                }
                if let errorMessage = dataState.exception {
                    log.e(message: {"Displaying error: \(errorMessage)"})
                }
            }
        }
    func deactivate() {
           viewModel?.onDestroy()
           viewModel = nil
       }
}

struct WeatherScreen: View {
    @ObservedObject
    var observableModel = ObservableWeatherModel()

    var body: some View {
        WeatherViewContent(
            loading: observableModel.loading,
            weatherReport: observableModel.weatherReport,
            error: observableModel.error
        )
        .onAppear(perform: {
            observableModel.activate()
        })
        .onDisappear(perform: {
            observableModel.deactivate()
        })
    }
}


struct WeatherViewContent: View {
    var loading: Bool
    var weatherReport: WeatherReport?
    var error: String?
    
    var body: some View {
        HStack(alignment: .center){
                 VStack {
                     Text(weatherReport?.cityTitle ?? "Loading").bold().font(Font.custom("", size: 60.0))
                     Text(weatherReport?.countryTitle ?? "Loading").bold()
                     HStack {
                         Text(weatherReport?.temperature ?? "Loading").padding(30)
                         Text(weatherReport?.humidity ?? "Loading").padding(30)
                     }
                     HStack {
                         Text(weatherReport?.windSpeed ?? "Loading").padding(30)
                         Text(weatherReport?.airPressure ?? "Loading").padding(30)
                     }
                 }
         }
     }
}


