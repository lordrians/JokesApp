package com.example.jokesapp.domain.usecase

import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import kotlinx.coroutines.flow.Flow

interface JokeUseCase {
    fun getAllJokes(): Flow<Resource<List<JokesEntity>>>
}