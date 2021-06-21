package com.example.jokesapp.data.source.remote.network

import com.example.jokesapp.BuildConfig
import com.example.jokesapp.data.source.remote.response.ResponseJokes
import retrofit2.http.GET

interface ApiService {
    @GET("${BuildConfig.BASE_URL}jokes/ten")
    suspend fun getAllJokes(): ResponseJokes
}