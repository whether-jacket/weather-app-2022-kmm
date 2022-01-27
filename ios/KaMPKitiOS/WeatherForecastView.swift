//
//  File.swift
//  KaMPKitiOS
//
//  Created by mo on 1/26/22.
//  Copyright © 2022 Touchlab. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct WeatherView: View {
    @StateObject private var sharedViewModel: SharedViewModel = ViewModel.getSharedViewModel.asStateObject
    
    var body: some View {
            
    }
}

struct WeatherScreen: View {
    var body: some View {
        WeatherViewContent(weatherReport: observableModel.weatherReport)
    }
}


struct WeatherViewContent: View {
    var weatherReport: WeatherReport?
    var body: some View {
        HStack(alignment: .center){
                 VStack {
                     Text(weatherReport?.cityTitle ?? "Loading...").bold().font(TextStyle.headline.getFont())
                     Text(weatherReport?.countryTitle ?? "").bold()
                     Divider()
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(weatherReport?.temperature ?? "")
                         Text(weatherReport?.humidity ?? "")
                     }.padding(SurroundingSpacings.large)
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(weatherReport?.windSpeed ?? "")
                         Text(weatherReport?.airPressure ?? "")
                     }.padding(SurroundingSpacings.large)
                 }.multilineTextAlignment(.center)
        }.font(TextStyle.body.getFont())
     }
}
