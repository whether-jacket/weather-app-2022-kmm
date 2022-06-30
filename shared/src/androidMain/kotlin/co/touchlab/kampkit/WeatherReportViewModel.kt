package co.touchlab.kampkit

import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kampkit.metaweather.viewmodel.WeatherReportContract
import co.touchlab.kampkit.metaweather.viewmodel.WeatherReportInputHandler
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.core.AndroidViewModel
import com.copperleaf.ballast.forViewModel

class WeatherReportViewModel(
    weatherUseCase: WeatherUseCase,
    configBuilder: BallastViewModelConfiguration.Builder
) : AndroidViewModel<
    WeatherReportContract.Inputs,
    WeatherReportContract.Events,
    WeatherReportContract.ViewState>(
    config = configBuilder
        .forViewModel(
            initialState = WeatherReportContract.ViewState(),
            inputHandler = WeatherReportInputHandler(weatherUseCase),
            name = "Weather Report"
            )
) {
    init {
        trySend(WeatherReportContract.Inputs.GetWeatherReport)
    }
}