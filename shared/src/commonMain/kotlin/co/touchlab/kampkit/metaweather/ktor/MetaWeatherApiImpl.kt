package co.touchlab.kampkit.metaweather.ktor

import co.touchlab.kampkit.metaweather.model.WeatherForLocation
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application.Json
import kotlinx.serialization.json.Json

class MetaWeatherApiImpl : MetaWeatherApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    level = LogLevel.ALL
                }
            }
            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getWeatherFromApi(): WeatherForLocation {
        return client.get(MetaWeatherApi.COMPLETE_URL)
    }
}