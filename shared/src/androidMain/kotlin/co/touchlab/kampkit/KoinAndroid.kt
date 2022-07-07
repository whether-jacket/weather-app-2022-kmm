package co.touchlab.kampkit

import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    viewModel { WeatherReportViewModel(get(), get()) }

    single<Settings> {
        AndroidSettings(get())
    }
}
