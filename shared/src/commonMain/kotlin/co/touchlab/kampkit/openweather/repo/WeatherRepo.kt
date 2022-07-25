package co.touchlab.kampkit.openweather.repo

import co.touchlab.kampkit.openweather.ktor.OpenWeatherApi
import co.touchlab.kampkit.openweather.model.openweather.WeatherForCity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepo : KoinComponent {
    private val weatherApi: OpenWeatherApi by inject()

    suspend fun getWeather(): WeatherForCity {
        return weatherApi.getWeatherFromApi()
    }
}
