package com.example.todolistapp.data.source.local

import com.example.todolistapp.data.source.remote.IRemoteTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalTaskRepository @Inject constructor(): ILocalTaskRepository {

    private var _localTaskList = mutableListOf<LocalTask>()
    private val _localTaskStream = MutableStateFlow<List<LocalTask>>(_localTaskList.toList())

    override suspend fun readAll(): List<LocalTask> = _localTaskList.toList()

    override suspend fun readOne(id: String): LocalTask {
        TODO("Not yet implemented")
    }

    override suspend fun create(localTask: LocalTask): LocalTask {
        _localTaskList.add(localTask)
        _localTaskStream.value = _localTaskList.toList()
        return localTask
    }

    override suspend fun createAll(localTaskList: List<LocalTask>): List<LocalTask> {
        _localTaskList.addAll(localTaskList)
        _localTaskStream.value = _localTaskList
        return localTaskList
    }

    override suspend fun update(localTask: LocalTask): LocalTask {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        val newLocalTaskList = _localTaskList.filter { it.id != id }
        _localTaskStream.value = newLocalTaskList
    }

    override suspend fun deleteAll() {
        _localTaskList.clear()
        _localTaskStream.value = _localTaskList
    }

    override fun getStream(): Flow<List<LocalTask>> {
        return _localTaskStream
    }

}