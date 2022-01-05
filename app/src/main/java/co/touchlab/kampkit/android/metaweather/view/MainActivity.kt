package co.touchlab.kampkit.android.metaweather.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.produceState
import androidx.lifecycle.Observer
import co.touchlab.kampkit.android.metaweather.design.KaMPKitTheme
import co.touchlab.kampkit.android.metaweather.viewmodel.ViewModel
import co.touchlab.kampkit.injectLogger
import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val log: Logger by injectLogger("MainActivity")
    private val viewModel: ViewModel by lazy {
        ViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewStateObserver = Observer<ViewState> { viewState ->
            setNewViewState(viewState)
        }
        viewModel.viewStateLiveData.observe(this, viewStateObserver)
        setContent {
            KaMPKitTheme {

            }
        }
        viewModel.startApp()
    }

    private fun setNewViewState(viewState: ViewState) {
        setContent {
            if (viewState.isLoadingNetworkRequest){
                LoadingScreen()
            }else{
                WeatherReportView(weatherStats = viewState.weatherStats)
            }
        }
    }
}
