package co.touchlab.kampkit.metaweather.model

sealed class Response<T> {
    class Success<T>(val data: T) : Response<T>()
    class Failure<T>(val message: String) : Response<T>()
}
