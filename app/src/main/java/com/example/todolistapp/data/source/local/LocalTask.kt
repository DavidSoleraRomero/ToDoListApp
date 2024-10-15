package com.example.todolistapp.data.source.local

import java.time.Instant

data class LocalTask(
    val id: String,
    val title: String,
    val body: String,
    val completed: Boolean,
    val createdAt: Instant
)
