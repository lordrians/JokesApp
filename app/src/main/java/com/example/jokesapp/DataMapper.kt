package com.example.jokesapp

import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.data.source.remote.response.ResponseJokesItem

object DataMapper {
    fun mapEntitiesToDomain(input: List<JokesEntity>): List<JokesEntity> =
        input.map {
            JokesEntity(
                punchline = it.punchline,
                setup = it.setup,
                id = it.id,
                type = it.type
            )
        }

    fun mapResponseToEntities(input: List<ResponseJokesItem>): List<JokesEntity>{
        val jokesList = ArrayList<JokesEntity>()
        input.map {
            val joke = JokesEntity(
                punchline = it.punchline,
                setup = it.setup,
                id = it.id,
                type = it.type
            )
            jokesList.add(joke)
        }
        return jokesList
    }

}
