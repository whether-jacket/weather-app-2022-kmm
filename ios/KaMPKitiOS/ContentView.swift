//
//  ContentView.swift
//  KaMPKitiOS
//
//  Created by Ramzi Jabali on 1/12/22.
//  Copyright © 2022 Touchlab. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
         HStack {
             WeatherReportView(city: weather, country: country, temperature: temperature, humidity: humidity, windSpeed: windSpeed, airPressure: airPressure)
         }.position(x: 220, y: 200)
     }
}

struct WeatherReportView: View {
    var city: String
    var country: String
    var temperature: String
    var humidity: String
    var windSpeed: String
    var airPressure: String
    
    var body: some View {
        VStack(){
            Text(city).bold().font(Font.custom("", size: 60.0))
            Text(country).bold()
            HStack{
                Text(temperature).padding(30)
                Text(humidity).padding(30)
            }
            HStack{
                Text(windSpeed).padding(30)
                Text(airPressure).padding(30)
            }
        }
    }
}
struct load{
    let weatherUseCase = WeatherUseCase(weatherRepo: WeatherRepo())
    var weather = "Loading..."
    var country = "Loading..."
    var temperature = "Loading..."
    var humidity = "Loading..."
    var windSpeed = "Loading..."
    var airPressure = "Loading..."
    let defaultValue = "error"
     
    weatherUseCase.getWeatherReport() { result,  error in
            switch result {
            case let successResponse as ResponseSuccess<WeatherReport>:
                weather = successResponse.data?.cityTitle ?? defaultValue
                country = successResponse.data?.countryTitle ?? defaultValue
                temperature = successResponse.data?.temperature ?? defaultValue
                humidity = successResponse.data?.humidity ?? defaultValue
                windSpeed = successResponse.data?.windSpeed ?? defaultValue
                airPressure = successResponse.data?.airPressure ?? defaultValue
                let viewController = UIHostingController(rootView: ContentView(weather: weather, country: country, temperature: temperature, humidity: humidity, windSpeed: windSpeed, airPressure: airPressure))
            case let errorResponse as ResponseFailure<WeatherReport>:
                weather = errorResponse.message
            default:
                weather = "We hit some response"
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(weather: "London", country: "England", temperature: "100F", humidity: "10%", windSpeed: "35MPH", airPressure: "100tons" )
    }
}
