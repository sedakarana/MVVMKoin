package com.sedakarana.todoapp.ui.viewmodel

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.sedakarana.todoapp.data.entity.TodoData
import com.sedakarana.todoapp.data.repo.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddViewModel(val todoRepository: TodoRepository) : ViewModel() {
    fun save(title: String) {
        CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            todoRepository.save(title)
        }
    }


}