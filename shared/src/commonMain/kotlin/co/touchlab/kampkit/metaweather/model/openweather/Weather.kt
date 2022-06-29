package co.touchlab.kampkit.metaweather.model.openweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("id")
    val id: Long,

    @SerialName("main")
    val main: String,

    @SerialName("description")
    val description: String,

    @SerialName("icon")
    val icon: String,

)
