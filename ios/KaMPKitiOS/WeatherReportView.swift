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
        viewModelFactory: {KotlinDependencies.shared.getWeatherReportViewModel()}
    )
    var body: some View {
        WeatherReportContent(
            vmState: observableModel.vmState,
            postInput: observableModel.postInput
        ).withViewModel(observableModel) {
            
        }
    }
}

struct WeatherReportContent: View {
    var vmState: WeatherReportContract.ViewState
    var postInput: (WeatherReportContract.Inputs) -> Void
    var body: some View {
        if vmState.weatherReport.isLoading() {Text("Loading...")} else{
        HStack(alignment: .center) {
            VStack {
                Text(vmState.weatherForecast.cityTitle ).bold().font(Font.title)
                Text(vmState.weatherForecast.countryTitle ).bold()
                Divider()
                HStack(spacing: HorizontalSpacings.x10) {
                    Text(vmState.weatherForecast.temperature )
                    Text(vmState.weatherForecast.humidity )
                }
                        .padding(SurroundingSpacings.large)
                HStack(spacing: HorizontalSpacings.x10) {
                    Text(vmState.weatherForecast.windSpeed )
                    Text(vmState.weatherForecast.airPressure )
                }
                        .padding(SurroundingSpacings.large)
            }
                    .multilineTextAlignment(.center)
        }
        }
    }
}
