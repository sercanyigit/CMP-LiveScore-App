package org.sercan.livescoreapp

import org.koin.core.context.startKoin
import org.sercan.livescoreapp.di.appModule

fun initKoin() {
    startKoin {
        modules(appModule)
    }
} 