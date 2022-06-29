package co.touchlab.kampkit.metaweather.repo

import co.touchlab.kampkit.injectLogger
import co.touchlab.kampkit.metaweather.ktor.OpenWeatherApi
import co.touchlab.kampkit.metaweather.model.WeatherForLocation
import co.touchlab.kampkit.metaweather.model.openweather.WeatherForCity
import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepo : KoinComponent {
    private val weatherApi: OpenWeatherApi by inject()
    private val log: Logger by injectLogger("WeatherRepo")

    suspend fun getWeather(): WeatherForCity {
        return weatherApi.getWeatherFromApi()
    }
}
