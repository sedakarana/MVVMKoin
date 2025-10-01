package com.sedakarana.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sedakarana.todoapp.data.repo.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(var todoRepository: TodoRepository): ViewModel() {
    fun update(id: Int, title: String, status: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            todoRepository.update(id, title, status)
        }
    }
}