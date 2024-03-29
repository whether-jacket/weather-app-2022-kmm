package co.touchlab.kampkit.openweather.viewmodel

import co.touchlab.kampkit.openweather.repo.WeatherReport
import com.copperleaf.ballast.repository.cache.Cached
import com.copperleaf.ballast.repository.cache.getCachedOrNull
import com.copperleaf.ballast.repository.cache.getValueOrElse

object WeatherReportContract {
    data class ViewState(
        val weatherReport: Cached<WeatherReport> = Cached.NotLoaded(),
        val isLoading: Boolean = false,
        val errorMessage: String = ""
    ){
        val weatherForecast: WeatherReport = weatherReport.getValueOrElse { WeatherReport() }
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
        data class WeatherReportUpdated(val weatherReport: Cached<WeatherReport>) : Inputs() {
            override fun toString(): String {
                return "WeatherReportUpdated(WeatherReport=${weatherReport::class.simpleName}[${weatherReport.getCachedOrNull()}])"
            }
        }
    }

    sealed class Events {

    }
}