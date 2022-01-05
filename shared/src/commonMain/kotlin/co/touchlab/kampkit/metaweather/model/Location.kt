package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

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