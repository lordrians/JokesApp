package com.example.jokesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jokesapp.domain.usecase.JokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeUseCase: JokeUseCase
): ViewModel() {
    val jokes = jokeUseCase.getAllJokes().asLiveData()
}