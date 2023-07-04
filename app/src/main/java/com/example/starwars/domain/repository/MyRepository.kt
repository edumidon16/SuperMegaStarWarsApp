package com.example.starwars.domain.repository

import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars

interface MyRepository {

    suspend fun getAllPeople(): List<CharacterInfoStarWars>
}