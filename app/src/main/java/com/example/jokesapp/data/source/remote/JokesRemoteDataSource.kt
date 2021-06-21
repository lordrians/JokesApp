package com.example.jokesapp.data.source.remote

import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.data.source.remote.network.ApiService
import com.example.jokesapp.data.source.remote.response.ResponseJokesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRemoteDataSource @Inject constructor(private val apiService: ApiService){

    suspend fun getAllJokes(): Flow<ApiResponse<List<ResponseJokesItem>>> {
        return flow {
            try {
                val response = apiService.getAllJokes()
                val dataJokes = response.responseJokes
                if (dataJokes != null){
                    if (dataJokes.isNotEmpty())
                        emit(ApiResponse.Success(dataJokes))
                    else
                        emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}