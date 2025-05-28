package com.justinale3362.chikanomaze.util

actual fun getNotificationManager(): NotificationManager {
    // Android implementation
    return object : NotificationManager {
        override fun init(id: String, tag: String, onFinish: () -> Unit) {
            // Android implementation
        }

    }
}