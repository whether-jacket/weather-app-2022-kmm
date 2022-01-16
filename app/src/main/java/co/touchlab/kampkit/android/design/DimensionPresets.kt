package co.touchlab.kampkit.android.design

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.design.Surrounding.double_spacing
import co.touchlab.kampkit.android.design.Surrounding.half_spacing
import co.touchlab.kampkit.android.design.Surrounding.quadruple_spacing
import co.touchlab.kampkit.android.design.Surrounding.quarter_spacing
import co.touchlab.kampkit.android.design.Surrounding.quintuple_spacing
import co.touchlab.kampkit.android.design.Surrounding.septuple_spacing
import co.touchlab.kampkit.android.design.Surrounding.sextuple_spacing
import co.touchlab.kampkit.android.design.Surrounding.spacing
import co.touchlab.kampkit.android.design.Surrounding.triple_spacing

private object Surrounding {
    val quarter_spacing = 2.dp
    val half_spacing = 4.dp
    val spacing = 8.dp
    val double_spacing = 16.dp
    val triple_spacing = 24.dp
    val quadruple_spacing = 32.dp
    val quintuple_spacing = 40.dp
    val sextuple_spacing = 48.dp
    val septuple_spacing = 56.dp
    val octuple_spacing = 64.dp
    val nonuple_spacing = 72.dp
}

object Spacing {
    object Horizontal {
        val xxs = quarter_spacing
        val xs = half_spacing
        val s = spacing
        val m = double_spacing
        val l = triple_spacing
        val xl = quadruple_spacing
        val xxl = quintuple_spacing
        val xxxl = sextuple_spacing
        val xl4 = septuple_spacing
    }

    object Vertical {
        val xxs = quarter_spacing
        val xs = half_spacing
        val s = spacing
        val m = double_spacing
        val l = triple_spacing
        val xl = quadruple_spacing
        val xxl = quintuple_spacing
        val xxxl = sextuple_spacing
        val xl4 = septuple_spacing
    }

    object Surrounding {
        val xxs = quarter_spacing
        val xs = half_spacing
        val s = spacing
        val m = double_spacing
        val l = triple_spacing
        val xl = quadruple_spacing
        val xxl = quintuple_spacing
        val xxxl = sextuple_spacing
        val xl4 = septuple_spacing
    }
}

object TextSize {
    val xxs = 12.sp
    val xs = 14.sp
    val s = 16.sp
    val m = 18.sp
    val l = 22.sp
    val xl = 26.sp
    val xxl = 32.sp
    val xxxl = 42.sp
    val xl4 = 64.sp
}

object LetterSpacing {
    const val xxs = -0.12
    const val xs = -0.09
    const val s = -0.05
    const val m = 0
    const val l = 0.15
    const val xl = 0.2
    const val xxl = 0.3
}

object CornerRadius {
    val xxs = 1.dp
    val xs = 2.dp
    val s = 4.dp
    val m = 6.dp
    val l = 12.dp
}

object Elevation {
    val xs = 2.dp
    val s = 4.dp
    val m = 6.dp
    val l = 12.dp
}

object Transparency {
    const val xxs = 0.90
    const val xs = 0.75
    const val s = 0.50
    const val m = 0.25
    const val l = 0.10
    const val xl = 0.05
    const val xxl = 0.03
}
