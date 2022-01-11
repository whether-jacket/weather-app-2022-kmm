package co.touchlab.kampkit.android.landingpage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kampkit.android.landingpage.view.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kampkit.metaweather.model.Response

class ViewModel(private val weatherUseCase: WeatherUseCase) :
    ContainerHost<ViewState, String>,
    ViewModel(), KoinComponent {

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