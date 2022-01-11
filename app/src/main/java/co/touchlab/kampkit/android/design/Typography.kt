package co.touchlab.kampkit.android.design

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.R

// Set of Material typography styles to start with
private val MerriWeather = FontFamily(
    font(R.font.merriweather_light, FontWeight.W300),
    font(R.font.merriweather_regular, FontWeight.W400),
    font(R.font.merriweather_med, FontWeight.W400),
)

val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

)
