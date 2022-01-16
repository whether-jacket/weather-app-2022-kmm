package co.touchlab.kampkit.metaweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConsolidatedWeather(
    @SerialName("id")
    val id: Long,

    @SerialName("weather_state_name")
    val weatherStateName: String,

    @SerialName("weather_state_abbr")
    val weatherStateAbbr: String,

    @SerialName("wind_direction_compass")
    val windDirectionCompass: String,

    @SerialName("created")
    val createdDateTime: String,

    @SerialName("applicable_date")
    val applicableDate: String,

    @SerialName("min_temp")
    val minTemp: Float,

    @SerialName("max_temp")
    val maxTemp: Float,

    @SerialName("the_temp")
    val theTemp: Float,

    @SerialName("wind_speed")
    val windSpeed: Float,

    @SerialName("wind_direction")
    val windDirection: Float,

    @SerialName("air_pressure")
    val airPressure: Float,

    @SerialName("humidity")
    val humidity: Float,

    @SerialName("visibility")
    val visibility: Float,

    @SerialName("predictability")
    val predictability: Float
)
