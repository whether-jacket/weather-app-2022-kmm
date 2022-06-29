package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.model.Response
import co.touchlab.kampkit.metaweather.repo.WeatherReport
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope

class WeatherReportInputHandler(private val weatherUseCase: WeatherUseCase) :
    InputHandler<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState> {
    override suspend fun InputHandlerScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.handleInput(
        input: WeatherReportContract.Inputs
    ) = when(input){
        is WeatherReportContract.Inputs.GetWeatherReport -> {
            WeatherReportContract.Inputs.ShowLoading
            when(val weatherReport = weatherUseCase.getWeatherReport()){
                is Response.Success -> {
                    WeatherReportContract.Inputs.StopLoading
                    updateState {
                        it.copy(weatherReport = weatherReport.data) }
                }
                is Response.Failure -> {
                    WeatherReportContract.Inputs.StopLoading
                    println("weather contract"+weatherReport.message)
                    updateState { it.copy(errorMessage = weatherReport.message) }
                }
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