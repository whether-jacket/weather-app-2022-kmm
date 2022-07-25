package co.touchlab.kampkit.openweather.model.openweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp")
    val temperature: Double,

    @SerialName("feels_like")
    val feelsLike: Double,

    @SerialName("temp_min")
    val minimumTemp: Double,

    @SerialName("temp_max")
    val maximumTemp: Double,

    @SerialName("pressure")
    val pressure: Double,

    @SerialName("humidity")
    val humidity: Double,
)
