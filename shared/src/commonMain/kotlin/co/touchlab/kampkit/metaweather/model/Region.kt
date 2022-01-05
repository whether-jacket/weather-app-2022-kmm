package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Region(
    @SerialName("title")
    val title: String,

    @SerialName("location_type")
    val locationType: String,

    @SerialName("woeid")
    val whereOnEarthId: Int,

    @SerialName("latt_long")
    val lat_long: String
)