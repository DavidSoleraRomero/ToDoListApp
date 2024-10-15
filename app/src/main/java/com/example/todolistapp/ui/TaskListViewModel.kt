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
class TaskListViewModel @Inject constructor(
    private val defaultTaskRepository: ITaskRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            defaultTaskRepository.getStream().collect {
                taskList ->
                if (taskList.isEmpty())
                    _uiState.value = TaskListUiState.Loading
                else
                    _uiState.value = TaskListUiState.Success(taskList)
            }
        }
    }

}

sealed class TaskListUiState {
    data object Loading: TaskListUiState()
    class Success(val taskList: List<Task>): TaskListUiState()
    class Error(val errorMessage: String): TaskListUiState()
}