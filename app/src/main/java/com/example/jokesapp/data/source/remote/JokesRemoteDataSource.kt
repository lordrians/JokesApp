package com.example.jokesapp.data.source.remote

import android.util.Log
import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.data.source.remote.network.ApiService
import com.example.jokesapp.data.source.remote.response.ResponseJokesItem
import com.google.gson.Gson
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
                val response = apiService

                val dataJokes = response.getAllJokes()
                if (dataJokes != null){
                    if (dataJokes.isNotEmpty())
                        emit(ApiResponse.Success(dataJokes))
                    else
                        emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.i("RemoteDataSource", "getAllJokes: ${e.message} ")
            }
        }.flowOn(Dispatchers.IO)
    }

}