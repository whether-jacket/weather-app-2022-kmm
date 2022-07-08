package co.touchlab.kampkit

import co.touchlab.kampkit.metaweather.repo.WeatherUseCase
import co.touchlab.kermit.Logger
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    userDefaults: NSUserDefaults,
    appInfo: AppInfo,
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single<Settings> { AppleSettings(userDefaults) }
        single { appInfo }
        single { doOnStartup }
    }
)

actual val platformModule = module {
    single { WeatherReportViewModel(get<WeatherUseCase>(), get<BallastViewModelConfiguration.Builder>()) }
}

// Access from Swift to create a logger
@Suppress("unused")
fun Koin.loggerWithTag(tag: String) =
    get<Logger>(qualifier = null) { parametersOf(tag) }

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getWeatherReportViewModel() = get<WeatherReportViewModel>()
}
