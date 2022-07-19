package co.touchlab.kampkit

import co.touchlab.kampkit.db.KaMPKitDb
import co.touchlab.kampkit.metaweather.repo.WeatherReportRepository
import co.touchlab.kampkit.vm.WeatherReportViewModel
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            KaMPKitDb.Schema,
            get(),
            "KampkitDb"
        )
    }

    single<Settings> {
        AndroidSettings(get())
    }

    single {
        OkHttp.create()
    }

    viewModel {
        WeatherReportViewModel(
            get<WeatherReportRepository>(),
            get<BallastViewModelConfiguration.Builder>()
        )
    }
}
