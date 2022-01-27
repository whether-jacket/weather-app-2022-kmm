import Foundation
import shared
import SwiftUI

@available(iOS 14.0, *)
struct WeatherView: View {
    @StateObject private var sharedViewModel: SharedViewModel = ViewModel.getSharedViewModel.asStateObject
    
    var body: some View {
        HStack(alignment: .center){
                 VStack {
                     Text(sharedViewModel.state.weatherReport.cityTitle ?? "Loading...").bold().font(TextStyle.headline.getFont())
                     Text(sharedViewModel.state.weatherReport.countryTitle ?? "").bold()
                     Divider()
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(sharedViewModel.state.weatherReport.temperature ?? "")
                         Text(sharedViewModel.state.weatherReport.humidity ?? "")
                     }.padding(SurroundingSpacings.large)
                     HStack(spacing: HorizontalSpacings.x10){
                         Text(sharedViewModel.state.weatherReport.windSpeed ?? "")
                         Text(sharedViewModel.state.weatherReport.airPressure ?? "")
                     }.padding(SurroundingSpacings.large)
                 }.multilineTextAlignment(.center)
        }.font(TextStyle.body.getFont())
    }
}
