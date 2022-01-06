package co.touchlab.kampkit.android.metaweather.view

import co.touchlab.kampkit.android.metaweather.States
import co.touchlab.kampkit.android.metaweather.model.WeatherStats

data class ViewState(
    val weatherStats: WeatherStats = WeatherStats(),
    val state: States = States.FINISHED
)
