package com.example.todolistapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.data.Task
import com.example.todolistapp.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.taskList
        recyclerView.adapter = TaskListAdapter(::toTaskDetail, ::shareTask)
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect {
                    uiState ->
                        when (uiState) {
                            is TaskListUiState.Error -> TODO()
                            TaskListUiState.Loading -> {}
                            is TaskListUiState.Success -> {
                                Log.d("State", uiState.taskList.toString())
                                (recyclerView.adapter as TaskListAdapter).submitList(uiState.taskList)
                            }
                        }
                }

            }

        }

        binding.createTaskButton.setOnClickListener{
            toCreateTask()
        }
    }

    private fun toTaskDetail(taskId: String) {
        val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(taskId)
        findNavController().navigate(action)
    }

    private fun shareTask(task: Task) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "La tarea ${task.title} - ${task.body} se te ha asignado"
        )
        val chooser = Intent.createChooser(intent, "")
        startActivity(chooser)
    }

    private fun toCreateTask() {
        val action = TaskListFragmentDirections.actionTaskListFragmentToCreateTaskFragment()
        findNavController().navigate(action)
    }

}