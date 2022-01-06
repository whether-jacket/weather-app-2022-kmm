package co.touchlab.kampkit.android.metaweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.touchlab.kampkit.android.metaweather.States
import co.touchlab.kampkit.android.metaweather.model.WeatherStats
import co.touchlab.kampkit.android.metaweather.view.ViewSideEffects
import co.touchlab.kampkit.android.metaweather.view.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import co.touchlab.kampkit.metaweather.repo.WeatherRepo
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ViewModel : ContainerHost<ViewState, ViewSideEffects>, ViewModel(), KoinComponent {

    override val container: Container<ViewState, ViewSideEffects> = container(ViewState())

    private val weatherRepo: WeatherRepo = WeatherRepo()

    private var viewState = ViewState()
    // val viewState: LiveData<ViewState> = container.stateFlow.asLiveData()

    fun startMakingApiCall() = intent {
        postSideEffect(
            ViewSideEffects("Starting API call", States.IN_PROGRESS)
        )
        // reduce {
        //     ViewState(state = States.IN_PROGRESS)
        // }
        loadWeatherReport()
    }

    private fun loadWeatherReport() = intent {
        postSideEffect(
            ViewSideEffects(
                text = "Making a Network call",
                state = States.IN_PROGRESS
            )
        )
        viewModelScope.launch {
            val weatherReport = weatherRepo.getWeather()
            val newViewState = viewState.copy(
                weatherStats =
                WeatherStats(
                    temperature = "${weatherReport.consolidatedWeather[0].theTemp} F",
                    airPressure = summarizeFloat(weatherReport.consolidatedWeather[0].airPressure),
                    cityTitle = "${weatherReport.cityTitle}",
                    countryTitle = "${weatherReport.parentRegion.title}",
                    windSpeed = summarizeFloat(weatherReport.consolidatedWeather[0].windSpeed),
                    humidity = "${weatherReport.consolidatedWeather[0].humidity}"
                ),
                state = States.FINISHED
            )
            reduce {
                newViewState
            }
        }
    }

    private fun summarizeFloat(float: Float): String {
        val weatherInt = (float * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }
}