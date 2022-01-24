package co.touchlab.kampkit.metaweather.viewmodel

import org.koin.dsl.KoinAppDeclaration

expect class DependencyInjection {
    fun initialiseDependencyInjection(block: KoinAppDeclaration = {})
}