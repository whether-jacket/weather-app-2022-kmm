package co.touchlab.kampkit.metaweather.viewmodel

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope

class WeatherReportEventHandler(
    private val displayErrorMessage: suspend (String) -> Unit
) : EventHandler<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState> {

    override suspend fun EventHandlerScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.handleEvent(
        event: WeatherReportContract.Events
    ) = when (event) {
        is WeatherReportContract.Events.ShowErrorMessage -> {
            displayErrorMessage(event.errorMessage)
        }
        is WeatherReportContract.Events.ShowLoadingScreen -> {
        }
    }
}