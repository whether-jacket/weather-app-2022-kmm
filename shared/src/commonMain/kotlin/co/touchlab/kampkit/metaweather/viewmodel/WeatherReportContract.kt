package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport

object WeatherReportContract {
    data class ViewState(
        val weatherReport: WeatherReport = WeatherReport(),
    )
    sealed class Inputs {
        //no Inputs
    }

    sealed class Events{
        data class ShowErrorMessage(val errorMessage: String): Events()
        data class ShowLoadingScreen(val loading: String = "Loading..."): Events()
    }
}