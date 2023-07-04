package com.example.starwars.data.service

import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleService @Inject constructor(
    private val api: StarWarsService
){
    suspend fun getPeopleList(): List<CharacterInfoStarWars>{
        return withContext(Dispatchers.IO){
            val response = api.getAllPeople()
            response.body()?: emptyList()
        }
    }
}