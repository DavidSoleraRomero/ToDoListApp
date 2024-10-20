package com.example.todolistapp.ui

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
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.databinding.FragmentCreateTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private lateinit var binding: FragmentCreateTaskBinding
    private val viewModel: TaskCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    uiState ->
                        when (uiState) {
                            TaskCreateUiState.Create -> {}
                        }
                }
            }
        }

        binding.createTaskButton.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val body = binding.bodyInput.text.toString()
            if (title.isNotEmpty() && body.isNotEmpty()) {
                viewModel.viewModelScope.launch {
                    viewModel.createTask(title, body)
                }
                findNavController().popBackStack()
            }
        }
    }

}