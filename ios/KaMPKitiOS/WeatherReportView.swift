import Combine
import SwiftUI
import shared

private let log = koin.loggerWithTag(tag: "ViewController")

struct WeatherReportView: View {
    @ObservedObject
    var observableModel = BallastObservable<
    WeatherReportContract.Inputs,
    WeatherReportContract.Events,
    WeatherReportContract.ViewState>(
        viewModelFactory: KotlinDependencies.shared.getWeatherReportViewModel,
            eventHandlerFactory: nil
    )
    var body: some View {
        WeatherReportContent(
            vmState: observableModel.vmState,
            postInput: observableModel.postInput
        ).withViewModel(observableModel){
            observableModel.postInput(WeatherReportContract.InputsGetWeatherReport())
        }
    }
}

struct WeatherReportContent: View {
    var vmState: WeatherReportContract.ViewState
    var postInput: (WeatherReportContract.Inputs) -> Void
    var body: some View {
        HStack(alignment: .center) {
            VStack {
                Text(vmState.weatherReport.cityTitle ).bold().font(TextStyle.headline.getFont())
                Text(vmState.weatherReport.countryTitle ).bold()
                Divider()
                HStack(spacing: HorizontalSpacings.x10) {
                    Text(vmState.weatherReport.temperature )
                    Text(vmState.weatherReport.humidity )
                }
                        .padding(SurroundingSpacings.large)
                HStack(spacing: HorizontalSpacings.x10) {
                    Text(vmState.weatherReport.windSpeed )
                    Text(vmState.weatherReport.airPressure )
                }
                        .padding(SurroundingSpacings.large)
            }
                    .multilineTextAlignment(.center)
        }
                .font(TextStyle.body.getFont())
    }
}

