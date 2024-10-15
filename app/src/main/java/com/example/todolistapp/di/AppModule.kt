package com.example.todolistapp.di

import com.example.todolistapp.data.DefaultTaskRepository
import com.example.todolistapp.data.ITaskRepository
import com.example.todolistapp.data.source.local.ILocalTaskRepository
import com.example.todolistapp.data.source.local.LocalTaskRepository
import com.example.todolistapp.data.source.remote.IRemoteTaskRepository
import com.example.todolistapp.data.source.remote.RemoteTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindLocalTaskRepository(localTaskRepository: LocalTaskRepository): ILocalTaskRepository

    @Binds
    abstract fun bindRemoteTaskRepository(remoteTaskRepository: RemoteTaskRepository): IRemoteTaskRepository

    @Binds
    abstract fun bindDefaultTaskRepository(defaultTaskRepository: DefaultTaskRepository): ITaskRepository

}