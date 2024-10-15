package com.example.todolistapp.data

import android.util.Log
import com.example.todolistapp.data.source.local.ILocalTaskRepository
import com.example.todolistapp.data.source.remote.IRemoteTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTaskRepository @Inject constructor(
    private val localTaskRepository: ILocalTaskRepository,
    private val remoteTaskRepository: IRemoteTaskRepository
): ITaskRepository {

    override suspend fun readAll(): List<Task> {
        refreshTasks()
        return withContext(Dispatchers.IO) {
            localTaskRepository.readAll().toTask()
        }
    }

    override suspend fun readOne(id: String): Task {
        TODO("Not yet implemented")
    }

    override suspend fun create(task: Task): Task {
        task.id = UUID.randomUUID().toString()
        localTaskRepository.create(task.toLocal())
        saveRemoteTasks()
        return task
    }

    override suspend fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Task {
        TODO("Not yet implemented")
    }

    override suspend fun getStream(): Flow<List<Task>> {
        return localTaskRepository.getStream().map {
            localTask -> withContext(Dispatchers.IO) {localTask.toTask()}
        }
    }

    private suspend fun saveRemoteTasks() {
        val actualLocalTasks = localTaskRepository.readAll()
        val newRemoteTasks = actualLocalTasks.toRemote()
        remoteTaskRepository.saveTasks(newRemoteTasks)
    }

    private suspend fun refreshTasks() {
        val remoteTasks = remoteTaskRepository.readAll()
        localTaskRepository.deleteAll()
        localTaskRepository.createAll(remoteTasks.toLocal())
    }

}