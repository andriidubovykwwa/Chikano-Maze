package com.justinale3362.chikanomaze.util

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


object RequestManager {
    private const val MAX_RETRIES = 4
    private const val TIMEOUT_MS = 21_000L
    private const val DELAY_MS = 1900L

    private const val START =
        "https://goldentracker.space/chikanomaze/data.json"
    private const val API_KEY = "F2Z40qEsjKgxTzHIxcknjOB4DfWtAAdZ"
    private const val API_KEY_APP = "Odiz6Sg9SQ2hLS9KcFDwlJ0T"

    private val client = createHttpClient {
        install(HttpRequestRetry) {
            maxRetries = MAX_RETRIES
            retryOnExceptionIf(MAX_RETRIES) { _, it -> it.message?.contains("1009") != true }
            retryOnServerErrors(MAX_RETRIES)
            delayMillis { retry ->
                retry * DELAY_MS
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_MS
            connectTimeoutMillis = TIMEOUT_MS
            socketTimeoutMillis = TIMEOUT_MS
        }
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
    }

    private suspend fun getContentResult(): ContentResult? {
        try {
            val result = client.get(START)
            if (result.status != HttpStatusCode.OK) return null
            return result.body()
        } catch (_: Exception) {
            return null
        }
    }

    private suspend fun getServiceResult(url: String): ServiceResult? {
        try {
            val result = client.get(url) {
                header("apikey", API_KEY)
                header("bundle", getBundle())
            }
            if (result.status != HttpStatusCode.OK) return null
            val responseBody = result.body<ServiceResult>()
            if (!checkUA(responseBody.cu)) return null
            return responseBody
        } catch (_: Exception) {
            return null
        }
    }

    private suspend fun getFinalResult(url: String): FinalResult? {
        try {
            val result = client.get(url) {
                header("apikeyapp", API_KEY_APP)
                header("ip", getIp())
                header("useragent", getUserAgent())
                header("langcode", getLocale())
            }
            if (result.status != HttpStatusCode.OK) return null
            return result.body()
        } catch (_: Exception) {
            return null
        }
    }

    suspend fun getRequestResult(): FinalResult? = withContext(Dispatchers.IO) {
        val contentResult = getContentResult()
        val content = contentResult?.content
        if (content.isNullOrBlank()) return@withContext null

        val serviceResult = getServiceResult(content.toString())
        val service = serviceResult?.service
        if (service.isNullOrBlank()) return@withContext null

        val finalResult = getFinalResult(service.toString())
        return@withContext if (!finalResult?.data.isNullOrBlank()) {
            finalResult
        } else {
            null
        }
    }

    private suspend fun getIp(): String {
        return try {
            client.get("https://api.ipify.org").bodyAsText()
        } catch (_: Exception) {
            ""
        }
    }

    private suspend fun checkUA(data: String): Boolean {
        return try {
            val response = client.get(data) {
                header("User-Agent", getUserAgent())
            }
            response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun getUserAgent(): String = withContext(Dispatchers.Main) {
        getUA().toString()
    }

    @Serializable
    data class ContentResult(
        @SerialName("content") val content: String
    )

    @Serializable
    data class ServiceResult(
        @SerialName("cloack_url") val cu: String,
        @SerialName("atr_service") val service: String
    )

    @Serializable
    data class FinalResult(
        @SerialName("final_url") val data: String,
        @SerialName("push_sub") val tag: String,
        @SerialName("os_user_key") val key: String
    )

}

expect fun getBundle(): String?
expect fun getLocale(): String?
expect fun getUA(): String?
expect fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient
