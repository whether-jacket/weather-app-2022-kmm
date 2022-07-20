package co.touchlab.kampkit.openweather.repo

import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.repository.BallastRepository
import com.copperleaf.ballast.repository.bus.EventBus
import com.copperleaf.ballast.repository.cache.Cached
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherReportRepository(
    coroutineScope: CoroutineScope,
    configBuilder: BallastViewModelConfiguration.Builder,
    eventBus: EventBus,
    weatherUseCase: WeatherUseCase,
) : BallastRepository<
    WeatherRepositoryContract.Inputs,
    WeatherRepositoryContract.State>(
    coroutineScope = coroutineScope,
    eventBus = eventBus,
    configBuilder = configBuilder
        .apply {
            initialState = WeatherRepositoryContract.State()
            inputHandler = WeatherReportRepositoryInputHandler(
                weatherUseCase = weatherUseCase,
                eventBus = eventBus
            )
            name = "Weather Report Repository"
        }
) {
    fun getWeatherReport(forceRefresh: Boolean) : Flow<Cached<WeatherReport>>{
        trySend(WeatherRepositoryContract.Inputs.Initialize)
        trySend(WeatherRepositoryContract.Inputs.RefreshWeatherReport(forceRefresh))
        return observeStates()
            .map { it.weatherReport }
    }
}