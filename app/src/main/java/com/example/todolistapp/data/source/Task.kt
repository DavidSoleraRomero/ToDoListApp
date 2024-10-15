package com.example.todolistapp.data.source

import java.time.Instant

data class Task(
    val id: String,
    val title: String,
    val body: String,
    val completed: Boolean,
    val createdAt: Instant
)