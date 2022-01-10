package co.touchlab.kampkit.metaweather.repo

import co.touchlab.kampkit.metaweather.model.Response
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException

class WeatherUseCase(private val weatherRepo: WeatherRepo) {

    suspend fun getWeatherReport(): Response<WeatherReport> {
        return try {
            val response = weatherRepo.getWeather()
            val weatherReport = WeatherReport(
                cityTitle = response.cityTitle,
                countryTitle = response.parentRegion.title,
                temperature = "Temperature\n${response.consolidatedWeather[0].theTemp} F",
                humidity = "Humidity\n${summarizeFloat(response.consolidatedWeather[0].humidity)}",
                windSpeed = "Wind Speed\n${summarizeFloat(response.consolidatedWeather[0].windSpeed)}",
                airPressure = "Air Pressure\n${summarizeFloat(response.consolidatedWeather[0].airPressure)}"
            )
            Response.Success(weatherReport)
        } catch (e: RedirectResponseException) {
            //3xx: response
            Response.Failure("${e.message}: ${e.response}")
        } catch (e: ClientRequestException) {
            //4xx: response
            Response.Failure("${e.message}: ${e.response}")
        } catch (e: ServerResponseException) {
            //5xx: response
            Response.Failure("${e.message}: ${e.response}")
        } catch (t: Throwable) {
            Response.Failure("${t.message}: ${t.cause}")
        }
    }

    private fun summarizeFloat(float: Float): String {
        val weatherInt = (float * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }

    private suspend fun dinky() {
        when (val weatherReport = getWeatherReport()) {
            is Response.Success -> {
                weatherReport.data
            }
            is Response.Failure -> {
                weatherReport.message
            }
        }
    }
}