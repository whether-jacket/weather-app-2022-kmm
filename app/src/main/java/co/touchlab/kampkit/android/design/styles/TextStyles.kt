package co.touchlab.kampkit.android.design.styles

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import co.touchlab.kampkit.android.design.Colors
import co.touchlab.kampkit.android.design.TextSize

object TextStyles {
    val xxl = TextStyle(
        fontSize = TextSize.xxl,
        color = Colors.DimGray,
        textAlign = TextAlign.Center,
    )
    val xxxl =
        TextStyle(
            fontSize = TextSize.xxxl,
            color = Colors.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
}
