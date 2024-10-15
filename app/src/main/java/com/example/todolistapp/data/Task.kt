package com.example.todolistapp.data

import java.time.Instant

data class Task(
    var id: String,
    var title: String,
    var body: String,
    var completed: Boolean,
    val createdAt: Instant
)