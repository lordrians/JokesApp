package com.example.jokesapp.di

import android.content.Context
import androidx.room.Room
import com.example.jokesapp.data.source.local.room.JokesDao
import com.example.jokesapp.data.source.local.room.JokesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext mContext: Context): JokesDatabase{
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("JokesApp".toCharArray())
        val factory = SupportFactory(passPhrase)
        val builderDB = Room.databaseBuilder(mContext, JokesDatabase::class.java, "JokesApp.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
        return builderDB.build()
    }
    @Singleton
    @Provides
    fun provideJokesDao(database: JokesDatabase): JokesDao = database.jokesDao()

}