package co.touchlab.kampkit.android.landingpage.view

import co.touchlab.kampkit.metaweather.repo.WeatherReport

data class ViewState(
    val weatherReport: WeatherReport = WeatherReport(),
    val errorMessage: String = "",
    val isInProgress: Boolean = false
)
