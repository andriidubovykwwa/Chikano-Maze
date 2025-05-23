package com.justinale3362.chikanomaze.util

import org.jetbrains.skiko.OS
import org.jetbrains.skiko.OSVersion
import org.jetbrains.skiko.available
import platform.Foundation.setValue
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientation
import platform.UIKit.UIInterfaceOrientationMaskAllButUpsideDown
import platform.UIKit.UIInterfaceOrientationMaskPortrait
import platform.UIKit.UIInterfaceOrientationPortrait
import platform.UIKit.UIInterfaceOrientationUnknown
import platform.UIKit.setNeedsUpdateOfSupportedInterfaceOrientations

class IOSOrientationController : OrientationController {
    private val shouldUseNewOrientationAPI = available(OS.Ios to OSVersion(16))
    private var _orientation = Orientation.ALL
    override var orientation: Orientation
        get() = _orientation
        set(value) {
            _orientation = value
            appOrientation = value.mask()
            if (shouldUseNewOrientationAPI) {
                UIApplication.sharedApplication.keyWindow
                    ?.rootViewController
                    ?.setNeedsUpdateOfSupportedInterfaceOrientations()
            } else {
                UIDevice.currentDevice.setValue(
                    value.interfaceOrientation(),
                    forKey = "orientation"
                )
            }
        }

    private fun Orientation.interfaceOrientation(): UIInterfaceOrientation {
        return when (this) {
            Orientation.ALL -> UIInterfaceOrientationUnknown
            Orientation.PORTRAIT -> UIInterfaceOrientationPortrait
        }
    }

    private fun Orientation.mask(): ULong {
        return when (this) {
            Orientation.ALL -> UIInterfaceOrientationMaskAllButUpsideDown
            Orientation.PORTRAIT -> UIInterfaceOrientationMaskPortrait
        }
    }

    companion object {
        var appOrientation = UIInterfaceOrientationMaskAllButUpsideDown
    }
}

actual fun getOrientationController(): OrientationController = IOSOrientationController()