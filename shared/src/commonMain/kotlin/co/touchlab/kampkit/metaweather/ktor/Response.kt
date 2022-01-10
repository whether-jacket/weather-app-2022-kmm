package co.touchlab.kampkit.metaweather.ktor

import co.touchlab.kampkit.metaweather.model.WeatherForLocation
import co.touchlab.kampkit.metaweather.repo.HttpStatusCodeState
import io.ktor.http.HttpStatusCode

data class Response(
    val weatherForLocation: WeatherForLocation,
    val httpStatusCodeState: HttpStatusCodeState
)
