package com.example.todolistapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.ITaskRepository
import com.example.todolistapp.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val defaultTaskRepository: ITaskRepository
) : ViewModel() {

    suspend fun createTask(title: String, body: String) {
        defaultTaskRepository.create(
            Task(
                "",
                title,
                body,
                false,
                Instant.now()
            )
        )
    }

}