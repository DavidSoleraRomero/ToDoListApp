package com.example.todolistapp.data.source.remote

interface IRemoteTaskRepository {

    suspend fun readAll(): List<RemoteTask>
    suspend fun saveTasks(tasksList: List<RemoteTask>)

}