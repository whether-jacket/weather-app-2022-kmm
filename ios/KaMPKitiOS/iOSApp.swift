import shared
import SwiftUI

@available(iOS 14.0, *)
@main
struct iOSApp: App {
    
    init() {
        startKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            WeatherView()
        }
    }
}
