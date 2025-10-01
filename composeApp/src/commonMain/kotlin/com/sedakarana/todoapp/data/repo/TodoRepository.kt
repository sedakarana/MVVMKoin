package com.sedakarana.todoapp.data.repo

import com.sedakarana.todoapp.data.datasource.TodoDataSource

class TodoRepository() {
    var todoDataSource = TodoDataSource()
    suspend fun search(text: String) = todoDataSource.search(text)

    suspend fun updateStatus(id: Int) = todoDataSource.updateStatus(id)

    suspend fun deleteTask(id: Int) = todoDataSource.deleteTask(id)

    suspend fun update(id: Int, title: String, status: Boolean) =
        todoDataSource.update(id, title, status)

    suspend fun save(title: String) = todoDataSource.save(title)

    suspend fun loadAll() = todoDataSource.loadAll()
}