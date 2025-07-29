package ir.khanbeiki.utils

import android.content.Context
import android.content.pm.PackageManager

lateinit var appContext: Context

actual fun isWatchDevice(): Boolean {
    return appContext.packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)
}

fun provideAppContext(context: Context) {
    appContext = context
}
