package com.example.starwars.data.features.starwarspeople.remote.implement

import com.example.starwars.data.features.starwarspeople.mappers.toDomain
import com.example.starwars.data.service.StarWarsService
import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class StarWarsImplement @Inject constructor(
    private val apiService: StarWarsService
) : MyRepository {

    override suspend fun getAllPeople(): Flow<List<PeopleResponse>> = flow {
        val character = apiService.getAllPeople()

        if (character.isSuccessful) {
            character.body()?.let { character ->
                emit(listOf(character.toDomain()))
            }
        } else {
            emit(emptyList())
        }
    }
}
