package co.touchlab.kampkit

import co.touchlab.kampkit.openweather.ktor.OpenWeatherApi
import co.touchlab.kampkit.openweather.ktor.OpenWeatherApiImpl
import co.touchlab.kampkit.openweather.repo.WeatherRepo
import co.touchlab.kampkit.openweather.repo.WeatherReportRepository
import co.touchlab.kampkit.openweather.repo.WeatherUseCase
import co.touchlab.kampkit.openweather.viewmodel.WeatherReportContract
import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.copperleaf.ballast.BallastLogger
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.core.LoggingInterceptor
import com.copperleaf.ballast.plusAssign
import com.copperleaf.ballast.repository.bus.EventBus
import com.copperleaf.ballast.repository.bus.EventBusImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.Clock
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            platformModule,
            coreModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin
    // doOnStartup is a lambda which is implemented in Swift on iOS side
    val doOnStartup = koin.get<() -> Unit>()
    doOnStartup.invoke()

    val kermit = koin.get<Logger> { parametersOf(null) }
    // AppInfo is a Kotlin interface with separate Android and iOS implementations
    val appInfo = koin.get<AppInfo>()
    kermit.v { "App Id ${appInfo.appId}" }

    return koinApplication
}

private val coreModule = module {
    single<Clock> {
        Clock.System
    }

    // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
    // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
    // that would likely go into platformModule expect/actual.
    // See https://github.com/touchlab/Kermit
    val baseLogger = Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "KampKit")
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }

    single<OpenWeatherApi> {
        OpenWeatherApiImpl()
    }
    factory<WeatherRepo> { WeatherRepo() }
    factory<BallastViewModelConfiguration.Builder> {
        BallastViewModelConfiguration.Builder()
            .apply {
                logger = { tag -> KermitBallastLogger(getWith(tag)) }
                this += LoggingInterceptor()
                initialState = WeatherReportContract.ViewState()
            }
    }
    factory<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
    single<EventBus> {
        EventBusImpl()
    }
    single<WeatherUseCase> { WeatherUseCase(get<WeatherRepo>()) }
    single <WeatherReportRepository>{ WeatherReportRepository(get(), get(), get(), get())  }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

expect val platformModule: Module

class KermitBallastLogger(val log: Logger) : BallastLogger {
    override fun debug(message: String) { log.d(message) }
    override fun info(message: String) { log.i(message) }
    override fun error(throwable: Throwable) { log.e(throwable) { "" } }
}
