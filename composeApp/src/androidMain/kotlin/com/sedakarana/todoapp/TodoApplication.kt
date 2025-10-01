package com.sedakarana.todoapp

import android.app.Application
import com.sedakarana.todoapp.data.di.initializeKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}