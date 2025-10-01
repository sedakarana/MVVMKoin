package com.sedakarana.todoapp.data.datasource

import com.sedakarana.todoapp.data.entity.TodoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class TodoDataSource {
    suspend fun updateStatus(id: Int) {
        println("Güncellenecek ID: $id")
    }

    suspend fun deleteTask(id: Int) {
        println("Silinecek ID: $id")
    }

    suspend fun update(id: Int, title: String, status: Boolean) {
        println("Görev Güncellendi: $title")
    }

    suspend fun save(title: String) {
        println("Görev Kaydedildi: $title")
    }

    suspend fun loadAll(): List<TodoData> = withContext(Dispatchers.IO) {
        val todoList = ArrayList<TodoData>()
        val detail = TodoData(1, "Mail Atılacak", false)
        val detail1 = TodoData(2, "Anneme Hediye Yollanacak", true)
        val detail3 = TodoData(4, "Kitap Okunacak", false)
        val detail4 = TodoData(5, "Markete Gidilecek", true)
        todoList.add(detail)
        todoList.add(detail1)
        todoList.add(detail3)
        todoList.add(detail4)
        return@withContext todoList
    }

    suspend fun search(text: String): List<TodoData> = withContext(Dispatchers.IO) {
        val todoList = ArrayList<TodoData>()
        val detail = TodoData(1, "Mail Atılacak", false)
        todoList.add(detail)
        return@withContext todoList
    }


}