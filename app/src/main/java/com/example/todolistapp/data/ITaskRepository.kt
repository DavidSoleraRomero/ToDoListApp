package com.example.todolistapp.data

import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun readAll(): List<Task>
    suspend fun readOne(id: String): Task
    suspend fun create(task: Task): Task
    suspend fun update(task: Task): Task
    suspend fun delete(id: String): Task
    suspend fun getStream(): Flow<List<Task>>
}