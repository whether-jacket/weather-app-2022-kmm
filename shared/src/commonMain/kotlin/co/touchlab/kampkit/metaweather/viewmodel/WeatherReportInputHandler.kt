package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport
import co.touchlab.kampkit.metaweather.repo.WeatherReportRepository
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.copperleaf.ballast.observeFlows
import com.copperleaf.ballast.repository.cache.getValueOrElse
import kotlinx.coroutines.flow.map

class WeatherReportInputHandler(private val weatherReportRepository: WeatherReportRepository) :
    InputHandler<
        WeatherReportContract.Inputs,
        WeatherReportContract.Events,
        WeatherReportContract.ViewState
        > {
    override suspend fun InputHandlerScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.handleInput(
        input: WeatherReportContract.Inputs
    ) = when (input) {
        is WeatherReportContract.Inputs.GetWeatherReport -> {
            observeFlows(
                "Observe Weather Report",
                weatherReportRepository
                    .getWeatherReport(true)
                    .map { WeatherReportContract.Inputs.WeatherReportUpdated(it) }
            )
        }
        is WeatherReportContract.Inputs.WeatherReportUpdated -> {
            updateState {
                it.copy(weatherReport = input.weatherReport)
            }
        }
        is WeatherReportContract.Inputs.ShowLoading -> {
            updateState { it.copy(isLoading = true) }
        }
        WeatherReportContract.Inputs.StopLoading -> {
            updateState { it.copy(isLoading = false) }
        }
    }
}