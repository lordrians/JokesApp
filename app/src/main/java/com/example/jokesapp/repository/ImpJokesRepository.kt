package com.example.jokesapp.repository

import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import kotlinx.coroutines.flow.Flow

interface ImpJokesRepository {
    fun getAllJokes(): Flow<Resource<List<JokesEntity>>>
}