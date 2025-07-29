package ir.khanbeiki

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ir.khanbeiki.di.initKoin
import java.awt.Dimension
import javax.swing.SwingUtilities

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KmpClothingStore",
            resizable = true,
            state = rememberWindowState(
                width = 800.dp,
                height = 600.dp
            ),
        ) {
            LaunchedEffect(Unit) {
                val awtWindow = SwingUtilities.getWindowAncestor(this@Window.window.rootPane)
                awtWindow?.apply {
                    minimumSize = Dimension(600, 400)
                    maximumSize = Dimension(1024, 768)
                }
            }
            App()
        }
    }
}