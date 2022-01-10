package co.touchlab.kampkit.android.metaweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kampkit.android.metaweather.States
import co.touchlab.kampkit.android.metaweather.view.ViewSideEffects
import co.touchlab.kampkit.android.metaweather.view.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kampkit.metaweather.model.Response

class ViewModel(private val weatherUseCase: WeatherUseCase) :
    ContainerHost<ViewState, ViewSideEffects>,
    ViewModel(), KoinComponent {

    override val container: Container<ViewState, ViewSideEffects> = container(ViewState())

    private var viewState = ViewState()

    fun startMakingApiCall() = intent {
        postSideEffect(
            ViewSideEffects("Starting API call")
        )
        reduce {
            ViewState(state = States.IN_PROGRESS)
        }
        loadWeatherReport()
    }

    private fun loadWeatherReport() = intent {
        postSideEffect(
            ViewSideEffects(
                text = "Making a Network call",
            )
        )

        viewModelScope.launch {
            var newViewState = ViewState()
            when (val weatherReport = weatherUseCase.getWeatherReport()) {
                is Response.Success -> {
                    newViewState = viewState.copy(
                        state = States.FINISHED,
                        weatherReport = weatherReport.data
                    )
                }
                is Response.Failure -> {
                    newViewState = viewState.copy(
                        state = States.FINISHED,
                        errorMessage = weatherReport.message
                    )
                }
            }
            reduce {
                newViewState
            }
        }
    }
}