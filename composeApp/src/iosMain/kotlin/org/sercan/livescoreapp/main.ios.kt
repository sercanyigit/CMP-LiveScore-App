package org.sercan.livescoreapp

import androidx.compose.ui.window.ComposeUIViewController
import org.sercan.livescoreapp.di.initKoin
import platform.UIKit.UIViewController

@Suppress("FunctionName")
fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController { App() }
} 