package com.example.jokesapp.di

import com.example.jokesapp.repository.ImpJokesRepository
import com.example.jokesapp.repository.JokesRepostiroy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(jokesRepostiroy: JokesRepostiroy): ImpJokesRepository
}