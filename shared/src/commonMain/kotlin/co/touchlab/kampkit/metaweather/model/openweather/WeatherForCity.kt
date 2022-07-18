package co.touchlab.kampkit.metaweather.model.openweather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForCity(
    @SerialName("coord")
    val coordinates: Coordinate,

    @SerialName("weather")
    val weather: Array< Weather>,

    @SerialName("base")
    val base: String,

    @SerialName("main")
    val temperatureInformation: Main,

    @SerialName("visibility")
    val visibility: Long,

    @SerialName("wind")
    val wind: Wind,

    @SerialName("clouds")
    val clouds: Clouds,

    @SerialName("dt")
    val dataReceivingTime: Long,

    @SerialName("sys")
    val basicInformation: WeatherAttributes,

    @SerialName("timezone")
    val timezone: Int,

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val cityName: String,

    @SerialName("cod")
    val cod: Int,





)
