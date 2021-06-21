package com.example.jokesapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokesapp.data.source.local.entity.JokesEntity

@Database(entities = [JokesEntity::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase(){
    abstract fun jokesDao(): JokesDao
}