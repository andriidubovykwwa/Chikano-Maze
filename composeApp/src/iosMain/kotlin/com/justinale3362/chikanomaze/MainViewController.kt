package com.justinale3362.chikanomaze

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplication
import platform.UIKit.setStatusBarHidden

fun MainViewController() = ComposeUIViewController(
    configure = {
        UIApplication.sharedApplication.setStatusBarHidden(hidden = true, animated = true)
    },
    content = { App() }
)