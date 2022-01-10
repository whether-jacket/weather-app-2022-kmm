package co.touchlab.kampkit.metaweather.repo

import co.touchlab.kampkit.metaweather.model.Response
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.http.HttpStatusCode

class WeatherUseCase(private val weatherRepo: WeatherRepo) {
    suspend fun getWeatherReport(): Response<WeatherReport> {
        return try {
            val response = weatherRepo.getWeather()
            val weatherReport = WeatherReport(
                cityTitle = response.cityTitle,
                countryTitle = response.parentRegion.title,
                temperature = "Temperature\n${response.consolidatedWeather[0].theTemp} F",
                humidity = "Humidity\n${roundFloatToTwoDecimalPlaces(response.consolidatedWeather[0].humidity)}",
                windSpeed = "Wind Speed\n${roundFloatToTwoDecimalPlaces(response.consolidatedWeather[0].windSpeed)}",
                airPressure = "Air Pressure\n${roundFloatToTwoDecimalPlaces(response.consolidatedWeather[0].airPressure)}"
            )
            Response.Success(weatherReport)
        } catch (t: Throwable) {
            val netWorkError = getErrorType(t)
            Response.Failure("Error code:\n${netWorkError.value}\nError Message:\n${netWorkError.description}")
        }
    }

    private fun getErrorType(t: Throwable): HttpStatusCode =
        when (t) {
            is ResponseException -> t.response.status
            is RedirectResponseException -> t.response.status
            is ClientRequestException -> t.response.status
            is ServerResponseException -> t.response.status
            else -> HttpStatusCode(400, "${t.message}\n${t.cause}")
        }

    private fun roundFloatToTwoDecimalPlaces(float: Float): String {
        val weatherInt = (float * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }
}