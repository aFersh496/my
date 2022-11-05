package com.example.myapplication

data class Todo(
    val id: Long,
    val title: String,
    val isDone: Boolean = false
)