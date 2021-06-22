package com.example.jokesapp.ui

import androidx.lifecycle.*
import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.domain.usecase.JokeUseCase
import com.example.jokesapp.repository.JokesRepostiroy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JokesRepostiroy,
    private val jokeUseCase: JokeUseCase
): ViewModel() {

    val jokes = jokeUseCase.getAllJokes().asLiveData()

}