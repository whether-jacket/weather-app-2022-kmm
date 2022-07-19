package co.touchlab.kampkit.metaweather.ktor

import co.touchlab.kampkit.metaweather.model.openweather.WeatherForCity

interface OpenWeatherApi {
    suspend fun getWeatherFromApi(): WeatherForCity

    companion object {
        private const val BASE_URL = "HTTPS://api.openweathermap.org"
        const val COMPLETE_URL = "$BASE_URL/data/2.5/weather?q=London&appid=2e75ee7603fa06447e9d6c346a1aea3b&units=metric"
    }
}
