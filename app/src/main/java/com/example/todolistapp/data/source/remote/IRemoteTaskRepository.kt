package com.example.todolistapp.data.source.remote

interface IRemoteTaskRepository {

    fun readAll(): List<RemoteTask>
    fun saveTasks(tasksList: List<RemoteTask>)

}