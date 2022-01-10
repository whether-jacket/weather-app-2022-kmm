package co.touchlab.kampkit.android.landingpage.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.touchlab.kampkit.android.landingpage.view.screens.LoadingScreen
import co.touchlab.kampkit.android.landingpage.view.screens.WeatherReportView
import co.touchlab.kampkit.android.landingpage.viewmodel.ViewModel
import co.touchlab.kampkit.injectLogger
import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class MainActivity : ComponentActivity(), KoinComponent {

    private val log: Logger by injectLogger("MainActivity")
    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(
            state = ::render,
            sideEffect = ::handleSideEffect,
            lifecycleOwner = this@MainActivity
        )
        viewModel.startMakingApiCall()
    }

    private fun render(viewState: ViewState) {
        setContent {
            if (viewState.isInProgress) {
                LoadingScreen()
            } else {
                WeatherReportView(weatherReport = viewState.weatherReport, errorMessage = viewState.errorMessage)
            }
        }
    }

    private fun handleSideEffect(text: String) {
        Log.i(MainActivity::class.toString(), text)
    }
}
