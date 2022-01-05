package co.touchlab.kampkit.android.metaweather.view
import co.touchlab.kampkit.android.metaweather.model.WeatherStats
data class ViewState(

    val isLoadingNetworkRequest:Boolean = false,
    val weatherStats: WeatherStats = WeatherStats(),

)




