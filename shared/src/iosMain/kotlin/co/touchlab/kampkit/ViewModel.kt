package co.touchlab.kampkit

import co.touchlab.kampkit.metaweather.viewmodel.SharedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModel : KoinComponent {
    fun getSharedViewModel() = get<SharedViewModel>()
}
