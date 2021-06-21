package com.example.jokesapp.domain.usecase

import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.repository.ImpJokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeInteractor @Inject constructor(
    private val jokesRepository: ImpJokesRepository
) : JokeUseCase{
    override fun getAllJokes() = jokesRepository.getAllJokes()
}