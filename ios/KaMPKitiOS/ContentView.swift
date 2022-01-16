//
//  ContentView.swift
//  KaMPKitiOS
//
//  Created by Ramzi Jabali on 1/12/22.
//  Copyright © 2022 Touchlab. All rights reserved.
//

import SwiftUI
import shared


private let log = koin.loggerWithTag(tag: "ViewController")

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
         HStack {
             WeatherReportView(weatherReport: weatherReport)
         }.position(x: 220, y: 200)
     }
}

struct WeatherReportView: View {
    var weatherReport: WeatherReport?
    
    var body: some View {
        VStack(){
            Text(weatherReport!.cityTitle).bold().font(Font.custom("", size: 60.0))
            Text(weatherReport!.countryTitle).bold()
            HStack{
                Text(weatherReport!.temperature).padding(30)
                Text(weatherReport!.humidity).padding(30)
            }
            HStack{
                Text(weatherReport!.windSpeed).padding(30)
                Text(weatherReport!.airPressure).padding(30)
            }
        }
    }
}

