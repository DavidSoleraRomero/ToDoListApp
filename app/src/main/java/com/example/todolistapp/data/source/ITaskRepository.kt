package com.example.todolistapp.data.source

interface ITaskRepository {
    fun readAll(): List<Task>
    fun readOne(id: String): Task
    fun create(task: Task): Task
    fun update(task: Task): Task
    fun delete(id: String): Task
}