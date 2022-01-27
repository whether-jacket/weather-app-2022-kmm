package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.model.Response
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kampkit.metaweather.viewmodel.ViewState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SharedViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel(), ContainerHost<ViewState, String>, KoinComponent {

    override val container: Container<ViewState, String> = container(ViewState())

    private var viewState = ViewState()

    fun startMakingApiCall() = intent {
        reduce {
            ViewState(isInProgress = true)
        }
        loadWeatherReport()
    }

    private fun loadWeatherReport() = intent {
        viewModelScope.launch {
            var newViewState = ViewState()
            when (val weatherReport = weatherUseCase.getWeatherReport()) {
                is Response.Success -> {
                    newViewState = viewState.copy(
                        weatherReport = weatherReport.data,
                        isInProgress = false
                    )
                }
                is Response.Failure -> {
                    newViewState = viewState.copy(
                        errorMessage = weatherReport.message,
                        isInProgress = false
                    )
                }
            }
            reduce {
                newViewState
            }
        }
    }
}