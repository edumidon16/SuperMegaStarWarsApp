package com.example.starwars.domain.repository

import com.example.starwars.domain.model.ModelCharacterDetailResponse
import com.example.starwars.domain.model.PeopleResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MyRepository {
    suspend fun getAllPeople(): Flow<List<PeopleResponse>>
    suspend fun getDetailCharacter(@Query("name") characterName: String): Flow<List<ModelCharacterDetailResponse>>
}