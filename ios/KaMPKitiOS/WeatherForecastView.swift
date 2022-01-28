import Foundation
import shared
import sharedOrbitSwift
import SwiftUI

@available(iOS 14.0, *)
struct WeatherView: View {
    @StateObject private var sharedViewModel: SharedViewModel = ViewModel.getSharedViewModel.asStateObject
    
    var body: some View {
        let state = sharedViewModel.state
        
        HStack(alignment: .center){
                 VStack {
                     Text(state.weatherReport.cityTitle ?? "Loading...").bold().font(TextStyle.headline.getFont())
                     Text(state.weatherReport.countryTitle ?? "").bold()
                     Divider()
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(state.weatherReport.temperature ?? "")
                         Text(state.weatherReport.humidity ?? "")
                     }.padding(SurroundingSpacings.large)
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(state.weatherReport.windSpeed ?? "")
                         Text(state.weatherReport.airPressure ?? "")
                     }.padding(SurroundingSpacings.large)
                 }.multilineTextAlignment(.center)
        }.font(TextStyle.body.getFont())
    }
}
