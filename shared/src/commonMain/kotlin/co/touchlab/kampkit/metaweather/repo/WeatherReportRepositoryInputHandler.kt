package co.touchlab.kampkit.metaweather.repo

import co.touchlab.kampkit.metaweather.model.Response
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.copperleaf.ballast.observeFlows
import com.copperleaf.ballast.postInput
import com.copperleaf.ballast.repository.bus.EventBus
import com.copperleaf.ballast.repository.bus.observeInputsFromBus
import com.copperleaf.ballast.repository.cache.fetchWithCache
import kotlinx.coroutines.flow.MutableStateFlow

class WeatherReportRepositoryInputHandler(
    private val weatherUseCase: WeatherUseCase,
    private val eventBus: EventBus
) : InputHandler<
    WeatherRepositoryContract.Inputs,
    Any,
    WeatherRepositoryContract.State> {
    override suspend fun InputHandlerScope<WeatherRepositoryContract.Inputs, Any, WeatherRepositoryContract.State>.handleInput(
        input: WeatherRepositoryContract.Inputs
    ) = when (input) {
        is WeatherRepositoryContract.Inputs.Initialize -> {
            val previousState = getCurrentState()
            if(!previousState.initialized){
                updateState { it.copy(initialized = true) }
                observeFlows(
                    key = "Observe inputs from EventBus",
                    eventBus
                        .observeInputsFromBus<WeatherRepositoryContract.Inputs>(),
                )
            }else{
                logger.debug("already initialized")
                noOp()
            }
        }
        is WeatherRepositoryContract.Inputs.ClearCaches -> {}
        is WeatherRepositoryContract.Inputs.RefreshAllCaches -> {
            val currentState = getCurrentState()
            if (currentState.weatherReportInitialized) {
                postInput(WeatherRepositoryContract.Inputs.RefreshWeatherReport(true))
            }
            Unit
        }
        is WeatherRepositoryContract.Inputs.RefreshWeatherReport -> {
            updateState { it.copy(weatherReportInitialized = true) }
            val weatherReport : MutableStateFlow<WeatherReport> = MutableStateFlow(WeatherReport())
            fetchWithCache(
                input = input,
                forceRefresh = input.forceRefresh,
                getValue = { it.weatherReport },
                updateState = { WeatherRepositoryContract.Inputs.WeatherReportUpdated(it) },
                doFetch = {
                    when(val weatherReportResponse = weatherUseCase.getWeatherReport()){
                        is Response.Success -> {
                            logger.info("WeatherReport API network result: $weatherReportResponse")
                            weatherReport.value = weatherReportResponse.data
                        }
                        else -> { logger.info("WeatherReport API network result: $weatherReportResponse") }
                    }
                },
                observe =  weatherReport,
            )
        }
        is WeatherRepositoryContract.Inputs.WeatherReportUpdated -> {
            updateState { it.copy(weatherReport = input.value) }
        }
    }
}