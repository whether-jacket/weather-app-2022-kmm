package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
