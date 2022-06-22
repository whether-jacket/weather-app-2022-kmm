package co.touchlab.kampkit.metaweather.viewmodel

import co.touchlab.kampkit.metaweather.repo.WeatherReport
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope

class WeatherReportInputHandler :
    InputHandler<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState> {
    override suspend fun InputHandlerScope<WeatherReportContract.Inputs, WeatherReportContract.Events, WeatherReportContract.ViewState>.handleInput(
        input: WeatherReportContract.Inputs
    ) {
        //No user inputs
    }
}