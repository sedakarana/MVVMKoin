package com.sedakarana.todoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform