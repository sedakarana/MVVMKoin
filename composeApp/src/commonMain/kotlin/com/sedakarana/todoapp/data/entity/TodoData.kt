package com.sedakarana.todoapp.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class TodoData(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)