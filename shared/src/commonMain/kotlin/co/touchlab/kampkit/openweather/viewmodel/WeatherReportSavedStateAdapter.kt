package co.touchlab.kampkit.openweather.viewmodel

import com.copperleaf.ballast.savedstate.RestoreStateScope
import com.copperleaf.ballast.savedstate.SaveStateScope
import com.copperleaf.ballast.savedstate.SavedStateAdapter

class WeatherReportSavedStateAdapter :
    SavedStateAdapter<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState> {
    override suspend fun RestoreStateScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.restore():
        WeatherReportContract.ViewState {
        return WeatherReportContract.ViewState()
    }

    override suspend fun SaveStateScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.save() {
        saveDiff({ this }) { viewState ->
            WeatherReportContract.ViewState(
                weatherReport = viewState.weatherReport,
                isLoading = viewState.isLoading,
                errorMessage = viewState.errorMessage
            )
        }
    }
}