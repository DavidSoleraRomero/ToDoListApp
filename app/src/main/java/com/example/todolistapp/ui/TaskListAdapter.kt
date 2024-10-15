package com.example.todolistapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolistapp.data.Task
import com.example.todolistapp.databinding.TaskViewBinding

class TaskListAdapter(
    private val toTaskDetail: ((taskId: String) -> Unit),
    private val shareTask: ((task: Task) -> Unit)
): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskComparer) {

    inner class TaskViewHolder(private val binding: TaskViewBinding): ViewHolder(binding.root) {

        fun bind(task: Task) {

            binding.taskTitle.text = task.title
            binding.taskBody.text = task.body
            binding.taskCompleted.isChecked = task.completed

            binding.root.setOnClickListener {
                toTaskDetail(task.id)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskListAdapter.TaskViewHolder {
        val binding: TaskViewBinding = TaskViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.TaskViewHolder, position: Int) {
        val task: Task = getItem(position)
        holder.bind(task)
    }

    object TaskComparer: DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title == newItem.title && oldItem.body == newItem.body && oldItem.completed == newItem.completed && oldItem.createdAt == newItem.createdAt
        }

    }

}