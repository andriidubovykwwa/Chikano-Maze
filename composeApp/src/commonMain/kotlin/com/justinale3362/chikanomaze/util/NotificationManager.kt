package com.justinale3362.chikanomaze.util


interface NotificationManager {

    fun init(id: String, tag: String, onFinish: () -> Unit)
}

class NotificationManagerDataHolder() {
    companion object {
        const val KEY = "6f2fbe4b-bb2b-4990-8aa8-125e2401ac81"
    }
}

expect fun getNotificationManager(): NotificationManager