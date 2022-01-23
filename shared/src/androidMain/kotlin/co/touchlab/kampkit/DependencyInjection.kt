package co.touchlab.kampkit

import android.app.Application
import co.touchlab.kampkit.metaweather.viewmodel.SharedViewModel
import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

actual class DependencyInjection(private val application: Application) {
    actual fun initialiseDependencyInjection(block: KoinAppDeclaration) {
        startKoin {
            androidContext(application)
            modules(coreModule(), androidModule())
            block()
        }
    }

    private fun androidModule() = module {
        viewModel { SharedViewModel(get<WeatherUseCase>()) }
    }
}