package com.example.jokesapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.data.source.remote.response.ResponseJokesItem
import com.example.jokesapp.domain.usecase.JokeUseCase
import com.example.jokesapp.repository.JokesRepostiroy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JokesRepostiroy,
    private val jokeUseCase: JokeUseCase
): ViewModel() {

    val dataLive: MutableLiveData<ApiResponse<List<ResponseJokesItem>>> = MutableLiveData()

    fun getLiveData() = viewModelScope.launch {
        val data = repository.getLiveData()
        data.collect {
            dataLive.value = it
        }
    }
    val jokes = jokeUseCase.getAllJokes().asLiveData()

}