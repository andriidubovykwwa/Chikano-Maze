package com.justinale3362.chikanomaze.util


interface OrientationController {
    var orientation: Orientation
}

enum class Orientation {
    ALL, PORTRAIT
}

expect fun getOrientationController(): OrientationController