package co.touchlab.kampkit.android.landingpage.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.touchlab.kampkit.android.landingpage.view.screens.LoadingScreen
import co.touchlab.kampkit.android.landingpage.view.screens.WeatherReportView
import co.touchlab.kampkit.injectLogger
import co.touchlab.kermit.Logger
import co.touchlab.kampkit.metaweather.viewmodel.SharedViewModel
import co.touchlab.kampkit.metaweather.viewmodel.ViewState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.orbitmvi.orbit.viewmodel.observe

class LandingPageActivity : ComponentActivity(), KoinComponent {

    private val log: Logger by injectLogger(LandingPageActivity::class.toString())
    private val viewModel: SharedViewModel by inject<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(
            state = ::render,
            sideEffect = ::handleSideEffect,
            lifecycleOwner = this@LandingPageActivity
        )
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
        log.i(text)
    }
}
