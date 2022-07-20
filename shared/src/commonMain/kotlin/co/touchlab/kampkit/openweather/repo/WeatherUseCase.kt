package co.touchlab.kampkit.openweather.repo

import co.touchlab.kampkit.openweather.model.Response
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException

import io.ktor.http.HttpStatusCode
import kotlin.math.floor

class WeatherUseCase(private val weatherRepo: WeatherRepo) {
    suspend fun getWeatherReport(): Response<WeatherReport> {
        return try {
            val response = weatherRepo.getWeather()
            val weatherReport = WeatherReport(
                cityTitle = response.cityName,
                countryTitle = response.basicInformation.country,
                temperature = "Temperature\n${response.temperatureInformation.temperature} C",
                humidity = "Humidity\n${floor(response.temperatureInformation.humidity).toInt()}",
                windSpeed = "Wind Speed\n${floor(response.wind.windSpeed).toInt()}",
                airPressure = "Air Pressure\n${floor(response.temperatureInformation.pressure).toInt()}"
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
}
