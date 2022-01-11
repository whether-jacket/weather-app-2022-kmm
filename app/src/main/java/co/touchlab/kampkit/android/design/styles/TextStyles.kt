package co.touchlab.kampkit.android.design.styles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import co.touchlab.kampkit.android.design.TextSize

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