package co.touchlab.kampkit.metaweather.repo

import org.koin.core.component.inject
import co.touchlab.kampkit.injectLogger
import co.touchlab.kampkit.metaweather.ktor.MetaWeatherApi
import co.touchlab.kampkit.metaweather.model.WeatherForLocation
import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent

class WeatherRepo : KoinComponent {
    private val weatherApi: MetaWeatherApi by inject()
    private val log: Logger by injectLogger("WeatherRepo")

    suspend fun getWeather(): WeatherForLocation{
        return weatherApi.getWeatherFromApi()
    }
}