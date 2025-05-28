package com.justinale3362.chikanomaze.util

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

actual fun getBundle(): String? {
    // Android implementation
    return ""
}

actual fun getLocale(): String? {
    // Android implementation
    return ""
}

actual fun getUA(): String? {
    // Android implementation
    return ""
}

actual fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    // Android implementation
    return HttpClient()
}