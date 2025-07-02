package ir.khanbeiki

import android.app.Application
import ir.khanbeiki.di.initKoin

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}