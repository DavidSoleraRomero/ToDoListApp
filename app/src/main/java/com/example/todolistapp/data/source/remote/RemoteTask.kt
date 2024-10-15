package com.example.todolistapp.data.source.remote

data class RemoteTask(
    val id: String,
    val title: String,
    val body: String,
    val completed: String,
    val createdAt: String
)
