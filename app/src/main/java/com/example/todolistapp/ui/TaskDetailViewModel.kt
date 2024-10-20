package com.example.todolistapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.ITaskRepository
import com.example.todolistapp.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val defaultRepository: ITaskRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    public val uiState: StateFlow<TaskDetailUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
        }
    }

    suspend fun requestTask(id: String) {
        val task: Task = defaultRepository.readOne(id)
        _uiState.value = TaskDetailUiState.TaskFound(task)
    }

}

sealed class TaskDetailUiState {
    data object Loading: TaskDetailUiState()
    class TaskFound(val task: Task): TaskDetailUiState()
}