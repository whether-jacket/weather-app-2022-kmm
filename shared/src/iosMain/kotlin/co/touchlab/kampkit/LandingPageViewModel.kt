package co.touchlab.kampkit

import co.touchlab.kampkit.metaweather.model.Response
import co.touchlab.kampkit.metaweather.repo.WeatherReport
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kampkit.models.DataState
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LandingPageViewModel(
    private val onDataState: (DataState<WeatherReport>) -> Unit
) : KoinComponent {
    private val log: Logger by injectLogger("LandingPageViewModel")
    private val weatherUseCase: WeatherUseCase by inject<WeatherUseCase>()
    private val scope = MainScope(Dispatchers.Main, log)
    private val _weatherStateFlow: MutableStateFlow<DataState<WeatherReport>> = MutableStateFlow(
        DataState(loading = true)
    )

    init {
        getWeatherForecast()
    }

    @OptIn(FlowPreview::class)
    fun getWeatherForecast() {
        scope.launch {
            log.v { "getWeatherReport: Collecting Statistics" }
            flowOf(
                weatherUseCase.getWeatherReport()
            ).collect { response ->
                when (response) {
                    is Response.Success -> {
                        _weatherStateFlow.value = DataState(data = response.data)
                    }
                    is Response.Failure -> {
                        _weatherStateFlow.value =
                            DataState(data = WeatherReport(cityTitle = response.message))
                    }
                }
            }
        }

        scope.launch {
            log.v { "Exposing flow through callbacks" }
            _weatherStateFlow.collect { dataState ->
                onDataState(dataState)
            }
        }
    }

    fun onDestroy() {
        scope.onDestroy()
    }
}
