package org.sercan.livescoreapp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
} 