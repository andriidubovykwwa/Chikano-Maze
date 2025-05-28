package com.justinale3362.chikanomaze.util

import OneSignal.OneSignal
import kotlinx.cinterop.ExperimentalForeignApi

class IOSNotificationManager : NotificationManager {

    @OptIn(ExperimentalForeignApi::class)
    override fun init(id: String, tag: String, onFinish: () -> Unit) {
        OneSignal.promptForPushNotificationsWithUserResponse {
            OneSignal.setExternalUserId(id)
            OneSignal.sendTag("sub_app", tag)
            onFinish()
        }
    }

}

actual fun getNotificationManager(): NotificationManager {
    return IOSNotificationManager()
}