package co.touchlab.kampkit.android.landingpage.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import co.touchlab.kampkit.WeatherReportViewModel
import co.touchlab.kampkit.android.landingpage.view.screens.LoadingScreen
import co.touchlab.kampkit.android.landingpage.view.screens.WeatherReportView
import co.touchlab.kampkit.injectLogger
import co.touchlab.kampkit.metaweather.viewmodel.WeatherReportContract
import co.touchlab.kermit.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class LandingPageActivity : ComponentActivity(), KoinComponent {

    private val log: Logger by injectLogger(LandingPageActivity::class.toString())
    private val viewModel: WeatherReportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.trySend(WeatherReportContract.Inputs.GetWeatherReport)
        setContent {
            val vmState by viewModel.observeStates().collectAsState()
            if(
                vmState.isLoading
            ){
                LoadingScreen()
            }
            WeatherReportView(weatherReport = vmState.weatherReport, errorMessage = vmState.errorMessage)
        }
    }
}
