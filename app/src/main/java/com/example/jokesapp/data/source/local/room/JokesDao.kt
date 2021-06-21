package com.example.jokesapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jokesapp.data.source.local.entity.JokesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {
    @Query("select * from tbl_jokes")
    fun getAllJokes(): Flow<List<JokesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(jokes: List<JokesEntity>)
}