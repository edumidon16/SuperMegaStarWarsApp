package com.example.starwars.data.features.starwarspeople.remote.implement

import com.example.starwars.data.features.starwarspeople.mappers.toDomain
import com.example.starwars.data.service.StarWarsService
import com.example.starwars.domain.model.ModelCharacterDetailResponse
import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class StarWarsImplement @Inject constructor(
    private val apiService: StarWarsService
) : StarWarsRepository {

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

    override suspend fun getDetailCharacter(name: String): Flow<List<ModelCharacterDetailResponse>> =
        flow {
            val characterDetail = apiService.getDetailCharacter(name)

            if (characterDetail.isSuccessful) {
                characterDetail.body()?.let { characterDetail ->
                    emit(listOf(characterDetail.toDomain()))
                }
            } else {
                emit(emptyList())
            }
        }
}
