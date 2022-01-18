import SwiftUI
import Foundation

enum TextStyle {
    case headline, subheadline, body, callout, footnote, caption1, caption2

    func getFont() -> Font {
        switch self {
        case .headline:
            return Font.custom("", size: 60.0)
        case .subheadline:
            return Font.custom("", size: 40)
        case .body:
            return Font.custom("", size: 20)
        case .callout:
            return Font.custom("", size: 60.0)
        case .footnote:
            return Font.custom("", size: 60.0)
        case .caption1:
            return Font.custom("", size: 12.0)
        case .caption2:
            return Font.custom("", size: 16.0)
        }
    }
}
