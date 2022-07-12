package co.touchlab.kampkit

import co.touchlab.kampkit.metaweather.repo.WeatherReportRepository
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    viewModel {
        WeatherReportViewModel(
            get<WeatherReportRepository>(),
            get<BallastViewModelConfiguration.Builder>()
        )
    }

    single<Settings> {
        AndroidSettings(get())
    }
}
