package com.example.starwars.domain.repository

import com.example.starwars.domain.model.ModelCharacterDetailResponse
import com.example.starwars.domain.model.PeopleResponse
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    suspend fun getAllPeople(): Flow<List<PeopleResponse>>
    suspend fun getDetailCharacter(characterName: String): Flow<List<ModelCharacterDetailResponse>>
}
