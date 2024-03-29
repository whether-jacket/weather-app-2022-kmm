package co.touchlab.kampkit.openweather.viewmodel

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope

class WeatherReportEventHandler(
    private val displayErrorMessage: suspend (String) -> Unit
) : EventHandler<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState> {

    override suspend fun EventHandlerScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.handleEvent(
        event: WeatherReportContract.Events
    ) {
    }
}