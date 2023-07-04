package com.example.starwars.data.features.starwarspeople.remote.implement

import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars
import com.example.starwars.data.features.starwarspeople.remote.model.RemotePeopleResponse
import com.example.starwars.data.service.PeopleService
import com.example.starwars.domain.repository.MyRepository
import javax.inject.Inject

open class StarWarsImplement @Inject constructor(
    private val apiService : PeopleService
): MyRepository {

    override suspend fun getAllPeople(): List<CharacterInfoStarWars> {
        return apiService.getPeopleList()
    }
}