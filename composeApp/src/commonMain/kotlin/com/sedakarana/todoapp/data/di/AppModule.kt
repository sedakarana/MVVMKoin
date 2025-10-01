package com.sedakarana.todoapp.data.di

import com.sedakarana.todoapp.data.datasource.TodoDataSource
import com.sedakarana.todoapp.data.repo.TodoRepository
import com.sedakarana.todoapp.ui.viewmodel.AddViewModel
import com.sedakarana.todoapp.ui.viewmodel.DetailViewModel
import com.sedakarana.todoapp.ui.viewmodel.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { TodoDataSource() }
    single { TodoRepository(get()) }

    viewModel { AddViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

fun initializeKoin(config: KoinAppDeclaration?= null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}
