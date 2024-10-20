package com.example.todolistapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.ITaskRepository
import com.example.todolistapp.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val defaultTaskRepository: ITaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskCreateUiState>(TaskCreateUiState.Create)
    val uiState: StateFlow<TaskCreateUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = TaskCreateUiState.Create
        }
    }

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

sealed class TaskCreateUiState {
    data object Create: TaskCreateUiState()
}