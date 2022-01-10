package co.touchlab.kampkit.metaweather.ktor

import co.touchlab.kampkit.metaweather.model.WeatherForLocation
import io.ktor.client.statement.HttpResponse

interface MetaWeatherApi {
    suspend fun getWeatherFromApi(): WeatherForLocation

    companion object {
        private const val BASE_URL = "https://www.metaweather.com"
        const val COMPLETE_URL = "$BASE_URL/api/location/44418"
    }
}
