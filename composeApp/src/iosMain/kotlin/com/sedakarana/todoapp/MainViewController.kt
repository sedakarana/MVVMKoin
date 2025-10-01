package com.sedakarana.todoapp

import androidx.compose.ui.window.ComposeUIViewController
import com.sedakarana.todoapp.data.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initializeKoin()
    }
)
{
    App()
}