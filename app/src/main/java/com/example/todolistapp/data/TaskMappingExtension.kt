package com.example.todolistapp.data

import com.example.todolistapp.data.source.local.LocalTask
import com.example.todolistapp.data.source.remote.RemoteTask
import java.time.Instant

fun Task.toLocal(): LocalTask {
    return LocalTask(
        this.id,
        this.title,
        this.body,
        this.completed,
        this.createdAt
    )
}

fun LocalTask.toRemote(): RemoteTask {
    return RemoteTask(
        this.id,
        this.title,
        this.body,
        this.completed.toString(),
        this.createdAt.toString()
    )
}

fun List<LocalTask>.toRemote(): List<RemoteTask> = map(LocalTask::toRemote)

fun LocalTask.toTask(): Task {
    return Task(
        this.id,
        this.title,
        this.body,
        this.completed,
        this.createdAt
    )
}

fun List<LocalTask>.toTask(): List<Task> = map(LocalTask::toTask)

fun RemoteTask.toLocal(): LocalTask {
    return LocalTask(
        this.id,
        this.title,
        this.body,
        this.completed.toBoolean(),
        Instant.parse(this.createdAt)
    )
}

fun List<RemoteTask>.toLocal(): List<LocalTask> = map(RemoteTask::toLocal)

