package co.touchlab.kampkit.vm

import co.touchlab.kampkit.metaweather.repo.WeatherReportRepository
import co.touchlab.kampkit.metaweather.viewmodel.WeatherReportContract
import co.touchlab.kampkit.metaweather.viewmodel.WeatherReportInputHandler
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.core.IosViewModel
import com.copperleaf.ballast.forViewModel

class WeatherReportViewModel(
    weatherReportRepository: WeatherReportRepository,
    configBuilder: BallastViewModelConfiguration.Builder
) : IosViewModel<
    WeatherReportContract.Inputs,
    WeatherReportContract.Events,
    WeatherReportContract.ViewState
    >(
    config = configBuilder
        .forViewModel(
            initialState = WeatherReportContract.ViewState(),
            inputHandler = WeatherReportInputHandler(weatherReportRepository),
            name = "WeatherReport"
        ),
){
    init {
        trySend(WeatherReportContract.Inputs.GetWeatherReport)
    }
}
