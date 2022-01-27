//
//  iOSApp.swift
//  KaMPKitiOS
//
//  Created by Ramzi Jabali on 1/25/22.
//  Copyright © 2022 Touchlab. All rights reserved.
//

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
            ContentView()
        }
    }
}
