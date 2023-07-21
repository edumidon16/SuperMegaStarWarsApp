package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    suspend operator fun invoke(): Flow<List<PeopleResponse>> = repository.getAllPeople()
}