package ir.khanbeiki.utils

import platform.UIKit.UIDevice

actual fun isWatchDevice(): Boolean {
    val model = UIDevice.currentDevice.model
    return model.contains("Watch", ignoreCase = true)
}