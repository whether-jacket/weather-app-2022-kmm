package co.touchlab.kampkit.metaweather.repo

data class WeatherReport(
    val cityTitle: String = "",
    val countryTitle: String = "",
    val temperature: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val airPressure: String = ""
)
