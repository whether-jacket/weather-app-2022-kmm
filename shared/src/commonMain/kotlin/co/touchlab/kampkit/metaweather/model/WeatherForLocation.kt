package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForLocation(
    @SerialName("consolidated_weather")
    val consolidatedWeather: Array<ConsolidatedWeather>,

    @SerialName("parent")
    val parentRegion: Region,

    @SerialName("woeid")
    val whereOnEarthId: Int,

    @SerialName("latt_long")
    val lat_long: String,

    @SerialName("title")
    val cityTitle: String,

    @SerialName("location_type")
    val locationType: String,

    @SerialName("time")
    val dateTime: String,

    @SerialName("timezone")
    val timezone: String,

    @SerialName("sun_rise")
    val sunriseDateTime: String,

    @SerialName("sun_set")
    val sunsetDateTime: String,

    @SerialName("timezone_name")
    val timezoneName: String
)