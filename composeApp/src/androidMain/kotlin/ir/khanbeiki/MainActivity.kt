package ir.khanbeiki

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ir.khanbeiki.utils.provideAppContext

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideAppContext(applicationContext)

        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
                            .background(MaterialTheme.colors.primary)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        App()
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                            )
                    )
                }
            }
        }
    }
}