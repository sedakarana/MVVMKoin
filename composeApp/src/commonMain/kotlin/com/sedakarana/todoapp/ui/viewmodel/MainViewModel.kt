package com.sedakarana.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sedakarana.todoapp.data.entity.TodoData
import com.sedakarana.todoapp.data.repo.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val todoRepository: TodoRepository) : ViewModel() {
    var todoList = MutableStateFlow<List<TodoData>>(listOf())

    fun search(text: String) {
        CoroutineScope(Dispatchers.Main).launch {
            todoList.value = todoRepository.search(text)
        }
    }

    fun updateStatus(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            todoRepository.updateStatus(id)
            loadAll()
        }
    }

    fun deleteTask(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            todoRepository.deleteTask(id)
            loadAll()
        }
    }

    fun loadAll() {
        CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            todoList.value = todoRepository.loadAll()
        }
    }
}