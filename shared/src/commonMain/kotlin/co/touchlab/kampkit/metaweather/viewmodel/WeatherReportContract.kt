package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport

object WeatherReportContract {
    data class ViewState(
        val weatherReport: WeatherReport = WeatherReport(),
        val isLoading: Boolean,
        val errorMessage: String
    )

    sealed class Inputs {
        object GetWeatherReport : Inputs()
        object ShowLoading : Inputs()
        object StopLoading : Inputs()
    }

    sealed class Events {

    }
}