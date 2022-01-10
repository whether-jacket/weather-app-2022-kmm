package co.touchlab.kampkit.metaweather.repo

data class WeatherStats(
    val temperature: String = "",
    val airPressure: String = "",
    val cityTitle: String = "",
    val countryTitle: String = "",
    val windSpeed: String = "",
    val humidity: String = ""
)
