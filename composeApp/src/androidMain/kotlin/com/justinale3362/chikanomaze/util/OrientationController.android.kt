package com.justinale3362.chikanomaze.util

class AndroidOrientationController : OrientationController {
    override var orientation: Orientation = Orientation.ALL
}

actual fun getOrientationController(): OrientationController = AndroidOrientationController()