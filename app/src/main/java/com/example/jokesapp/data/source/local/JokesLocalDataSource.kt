package com.example.jokesapp.data.source.local

import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.data.source.local.room.JokesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesLocalDataSource @Inject constructor(private val jokesDao: JokesDao){

    fun getAllJokes(): Flow<List<JokesEntity>> = jokesDao.getAllJokes()
    suspend fun insertJokes(jokes: List<JokesEntity>) = jokesDao.insertJoke(jokes)

}