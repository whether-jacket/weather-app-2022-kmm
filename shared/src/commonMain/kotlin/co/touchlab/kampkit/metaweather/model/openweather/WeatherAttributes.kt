package co.touchlab.kampkit.metaweather.model.openweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAttributes(
    @SerialName("type")
    val type: Int,

    @SerialName("id")
    val id: Long,

    @SerialName("country")
    val country: String,

    @SerialName("sunrise")
    val sunrise: Long,

    @SerialName("sunset")
    val sunset: Long,
)
