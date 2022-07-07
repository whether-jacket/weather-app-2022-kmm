package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport

object WeatherReportContract {
    data class ViewState(
        val weatherReport: WeatherReport = WeatherReport(),
        val isLoading: Boolean = false,
        val errorMessage: String = ""
    ){
        override fun toString(): String {
            return "ViewState(" +
                "Weather Report=${weatherReport::class.simpleName}, " +
                "error=$errorMessage, " +
                "isLoading=$isLoading" +
                ")"
        }
    }

    sealed class Inputs {
        object GetWeatherReport : Inputs()
        object ShowLoading : Inputs()
        object StopLoading : Inputs()
    }

    sealed class Events {

    }
}