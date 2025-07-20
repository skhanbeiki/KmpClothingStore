package ir.khanbeiki.wearclothingstore.presentation

import android.app.Application
import ir.khanbeiki.di.initKoin

class WearApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}