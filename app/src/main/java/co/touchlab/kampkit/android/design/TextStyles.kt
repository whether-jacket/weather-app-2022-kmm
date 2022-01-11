package co.touchlab.kampkit.android.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

object TextStyles {
    val xxl = TextStyle(
        fontSize = TextSize.xxl,
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
    )
    val xxxl =
        TextStyle(
            fontSize = TextSize.xxxl,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
}