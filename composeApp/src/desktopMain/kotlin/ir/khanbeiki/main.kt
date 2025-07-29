package ir.khanbeiki

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ir.khanbeiki.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KmpClothingStore",
        ) {
            App()
        }
    }
}