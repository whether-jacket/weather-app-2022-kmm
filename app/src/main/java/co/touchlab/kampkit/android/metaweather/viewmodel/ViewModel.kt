package co.touchlab.kampkit.android.metaweather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kampkit.android.metaweather.model.WeatherStats
import co.touchlab.kampkit.android.metaweather.view.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import co.touchlab.kampkit.metaweather.repo.WeatherRepo

class ViewModel : ViewModel(), KoinComponent {

    val viewStateLiveData: MutableLiveData<ViewState> by lazy {
        MutableLiveData<ViewState>()
    }
    private val weatherRepo: WeatherRepo = WeatherRepo()
    private var viewState = ViewState()

    fun startApp() {
        viewState = viewState.copy(isLoadingNetworkRequest = true)
        invalidateView()
        viewModelScope.launch {
            val weatherReport = weatherRepo.getWeather()
            viewState = viewState.copy(
                isLoadingNetworkRequest = false,
                weatherStats =
                WeatherStats(
                    temperature = "${weatherReport.consolidatedWeather[0].theTemp} F",
                    airPressure = summarizeFloat(weatherReport.consolidatedWeather[0].airPressure),
                    cityTitle = "${weatherReport.cityTitle}",
                    countryTitle = "${weatherReport.parentRegion.title}",
                    windSpeed = summarizeFloat(weatherReport.consolidatedWeather[0].windSpeed),
                    humidity = "${weatherReport.consolidatedWeather[0].humidity}"
                )
            )
            invalidateView()
        }
    }

    fun summarizeFloat(float: Float): String {
        val weatherInt = (float * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }

    private fun invalidateView() {
        viewStateLiveData.value = viewState
    }
}