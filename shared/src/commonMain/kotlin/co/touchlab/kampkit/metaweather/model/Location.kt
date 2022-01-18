package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("title")
    val cityTitle: String,

    @SerialName("location_type")
    val location_type: String,

    @SerialName("woeid")
    val whereOnEarthId: Int,

    @SerialName("latt_long")
    val lat_long: String
)
