package co.touchlab.kampkit.metaweather.ktor

import co.touchlab.kampkit.metaweather.model.WeatherForLocation

interface MetaWeatherApi {
    suspend fun getWeatherFromApi(): WeatherForLocation

    companion object {
        private const val BASE_URL = "HTTP://www.metaweather.com"
        const val COMPLETE_URL = "$BASE_URL/api/location/44418"
    }
}
