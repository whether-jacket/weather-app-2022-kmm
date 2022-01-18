import SwiftUI
import shared

private let log = koin.loggerWithTag(tag: "ObservableWeatherModel")

class ObservableWeatherModel: ObservableObject {
        private var viewModel: LandingPageViewModel?

        @Published
        var loading = false

        @Published
        var weatherReport: WeatherReport?

        @Published
        var error: String?

        func activate() {
            viewModel = LandingPageViewModel { [weak self] dataState in
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
                     Text(weatherReport?.cityTitle ?? "Loading...").bold().font(TextStyle.headline.getFont())
                     Text(weatherReport?.countryTitle ?? "").bold()
                     HStack {
                         Text(weatherReport?.temperature ?? "")
                         Text(weatherReport?.humidity ?? "")
                     }.padding(SurroundingSpacings.large)
                     HStack {
                         Text(weatherReport?.windSpeed ?? "")
                         Text(weatherReport?.airPressure ?? "")
                     }.padding(SurroundingSpacings.large)
                 }
        }.font(TextStyle.body.getFont())
     }
}


