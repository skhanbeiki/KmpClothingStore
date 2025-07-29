package ir.khanbeiki

import androidx.compose.ui.window.ComposeUIViewController
import ir.khanbeiki.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController { App() }
}
