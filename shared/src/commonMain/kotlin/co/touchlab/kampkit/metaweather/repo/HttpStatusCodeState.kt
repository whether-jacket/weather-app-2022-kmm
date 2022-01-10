package co.touchlab.kampkit.metaweather.repo

enum class HttpStatusCodeState(val code: Int) {
    HTTP_CODE_2XX(200),
    HTTP_CODE_3XX(300),
    HTTP_CODE_4XX(400),
    HTTP_CODE_5XX(500)
}