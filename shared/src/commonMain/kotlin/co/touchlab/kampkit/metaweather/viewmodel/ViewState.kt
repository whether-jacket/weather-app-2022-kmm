package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport

data class ViewState(
    val weatherReport: WeatherReport = WeatherReport(),
    val errorMessage: String = "",
    val isInProgress: Boolean = false
)
