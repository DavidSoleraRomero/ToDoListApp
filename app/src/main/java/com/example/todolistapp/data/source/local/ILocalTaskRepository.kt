package com.example.todolistapp.data.source.local

import kotlinx.coroutines.flow.Flow

interface ILocalTaskRepository {

    suspend fun readAll(): List<LocalTask>
    suspend fun readOne(id: String): LocalTask
    suspend fun create(localTask: LocalTask): LocalTask
    suspend fun createAll(localTaskList: List<LocalTask>): List<LocalTask>
    suspend fun update(localTask: LocalTask): LocalTask
    suspend fun delete(id: String)
    suspend fun deleteAll()
    fun getStream(): Flow<List<LocalTask>>

}