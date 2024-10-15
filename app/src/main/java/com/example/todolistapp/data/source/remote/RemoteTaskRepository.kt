package com.example.todolistapp.data.source.remote

import kotlinx.coroutines.delay
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteTaskRepository @Inject constructor(): IRemoteTaskRepository {

    private var remoteTaskList = mutableListOf<RemoteTask>()

    override suspend fun readAll(): List<RemoteTask> {
        delay(2000L)
        return this.remoteTaskList.toList();
    }

    override suspend fun saveTasks(tasksList: List<RemoteTask>) {
        delay(2000L)
        this.remoteTaskList = tasksList.toMutableList()
    }

}