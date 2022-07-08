package co.touchlab.kampkit.metaweather.repo

import co.touchlab.kampkit.metaweather.ktor.OpenWeatherApi
import co.touchlab.kampkit.metaweather.model.openweather.WeatherForCity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepo : KoinComponent {
    private val weatherApi: OpenWeatherApi by inject()

    suspend fun getWeather(): WeatherForCity {
        return weatherApi.getWeatherFromApi()
    }
}
