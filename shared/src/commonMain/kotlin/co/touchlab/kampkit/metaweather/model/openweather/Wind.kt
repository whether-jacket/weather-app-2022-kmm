package co.touchlab.kampkit.metaweather.model.openweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("speed")
    val windSpeed: Double,

    @SerialName("deg")
    val deg: Double,

    )
