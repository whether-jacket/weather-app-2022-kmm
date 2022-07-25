package co.touchlab.kampkit.openweather.repo

import com.copperleaf.ballast.repository.cache.Cached
import com.copperleaf.ballast.repository.cache.getCachedOrNull

object WeatherRepositoryContract {
    data class State(
        val initialized: Boolean = false,
        val weatherReportInitialized: Boolean = false,
        val weatherReport: Cached<WeatherReport> = Cached.NotLoaded()
    ) {
        override fun toString(): String {
            return "State(" +
                "initialized=$initialized, " +
                "breedsInitialized=$initialized, " +
                "breeds=${weatherReport::class.simpleName}[${weatherReport.getCachedOrNull()}]" +
                ")"
        }
    }

    sealed class Inputs {
        object ClearCaches : Inputs()
        object Initialize : Inputs()
        object RefreshAllCaches : Inputs()

        data class RefreshWeatherReport(val forceRefresh: Boolean) : Inputs()
        data class WeatherReportUpdated(val value: Cached<WeatherReport>) : Inputs() {
            override fun toString(): String {
                return "WeatherReportUpdated(value=${value::class.simpleName}[${value.getCachedOrNull()}])"
            }
        }
    }
}