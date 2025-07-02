package ir.khanbeiki.di

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

fun deinitKoin() {
    stopKoin()
}