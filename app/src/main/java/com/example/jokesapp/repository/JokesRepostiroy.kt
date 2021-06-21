package com.example.jokesapp.repository

import com.example.jokesapp.DataMapper
import com.example.jokesapp.data.NetworkBoundResource
import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.local.JokesLocalDataSource
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.data.source.remote.JokesRemoteDataSource
import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.data.source.remote.response.ResponseJokesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRepostiroy @Inject constructor(
    private val remoteDataSource: JokesRemoteDataSource,
    private val localDataSource: JokesLocalDataSource
): ImpJokesRepository {

    override fun getAllJokes(): Flow<Resource<List<JokesEntity>>> {
        return object : NetworkBoundResource<List<JokesEntity>, List<ResponseJokesItem>>() {
            override fun loadFromDB(): Flow<List<JokesEntity>> {
                return localDataSource.getAllJokes().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<JokesEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseJokesItem>>> =
                remoteDataSource.getAllJokes()

            override suspend fun saveCallResult(data: List<ResponseJokesItem>) {
                val jokes = DataMapper.mapResponseToEntities(data)
                localDataSource.insertJokes(jokes)
            }
        }.asFlow()
    }
}