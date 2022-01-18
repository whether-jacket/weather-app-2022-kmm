import SwiftUI
import Foundation

private struct Spacings {
    static let quarter = CGFloat(2)
    static let half = CGFloat(4)
    static let spacing = CGFloat(8)
    static let double = CGFloat(spacing * 2)             // 16
    static let triple = CGFloat(spacing * 3)             // 24
    static let quadruple = CGFloat(spacing * 4)          // 32
    static let quintuple = CGFloat(spacing * 5)          // 40
    static let sextuple = CGFloat(spacing * 6)           // 48
    static let septuple = CGFloat(spacing * 7)           // 56
    static let octuple = CGFloat(spacing * 8)            // 64
    static let nonuple = CGFloat(spacing  * 9)            // 72
    static let decuple = CGFloat(spacing * 10)           // 80
    static let undecuple = CGFloat(spacing * 11)         // 88
    static let duodecuple = CGFloat(spacing * 12)        // 96
    static let tredecuple = CGFloat(spacing * 13)        // 104
    static let quattuordecuple = CGFloat(spacing * 14)   // 112
    static let quindecuple = CGFloat(spacing * 15)       // 120
    static let sexdecuple = CGFloat(spacing * 16)        // 128
}

struct SurroundingSpacings {
    static let xxs = Spacings.quarter
    static let xsmall = Spacings.half
    static let small = Spacings.spacing
    static let medium = Spacings.double
    static let large = Spacings.quadruple
    static let xlarge = Spacings.octuple
    static let xl2 = Spacings.sexdecuple
}

struct HorizontalSpacings {
    static let xxs = Spacings.quarter
    static let xsmall = Spacings.half
    static let small = Spacings.spacing
    static let medium = Spacings.double
    static let large = Spacings.quadruple
    static let xlarge = Spacings.octuple
    static let xl2 = Spacings.sexdecuple
}

struct VerticalSpacings {
    static let xxs = Spacings.quarter
    static let xsmall = Spacings.half
    static let small = Spacings.spacing
    static let medium = Spacings.double
    static let large = Spacings.quadruple
    static let xlarge = Spacings.octuple
    static let xl2 = Spacings.sexdecuple
}

struct CornerRadius {
    static let xxs = 2
    static let xsmall = 4
    static let small = 6
    static let medium = 8
    static let large = 10
}

struct LetterSpacings {
    static let xxs: Float = -1.5
    static let xsmall: Float = -1.0
    static let small: Float = -0.5
    static let medium: Float = 0.0
    static let large: Float = 1.0
    static let xlarge: Float = 2.0
    static let xl2: Float = 3.0
}
