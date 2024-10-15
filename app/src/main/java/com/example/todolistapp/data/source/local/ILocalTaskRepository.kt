package com.example.todolistapp.data.source.local

import kotlinx.coroutines.flow.Flow

interface ILocalTaskRepository {

    suspend fun readAll(): List<LocalTask>
    suspend fun readOne(id: String): LocalTask
    suspend fun create(task: LocalTask): LocalTask
    suspend fun createAll(taskList: List<LocalTask>)
    suspend fun update(task: LocalTask): LocalTask
    suspend fun delete(id: String): LocalTask
    suspend fun deleteAll()
    fun getSteam(): Flow<List<LocalTask>>

}