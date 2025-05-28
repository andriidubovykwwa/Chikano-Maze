package com.justinale3362.chikanomaze.util

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.valueForKey
import platform.WebKit.WKWebView

actual fun getBundle(): String? {
    return NSBundle.mainBundle.bundleIdentifier
}

actual fun getLocale(): String? {
    return NSLocale.currentLocale.languageCode
}

actual fun getUA(): String? {
    return WKWebView().valueForKey("userAgent").toString()
}

actual fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
        block()
    }
}