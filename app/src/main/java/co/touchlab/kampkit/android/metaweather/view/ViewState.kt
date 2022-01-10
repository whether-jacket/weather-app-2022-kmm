package co.touchlab.kampkit.android.metaweather.view

import co.touchlab.kampkit.android.metaweather.States
import co.touchlab.kampkit.metaweather.repo.WeatherReport

data class ViewState(
    val weatherReport: WeatherReport = WeatherReport(),
    val errorMessage: String = "",
    val state: States = States.FINISHED
)
