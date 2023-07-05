package com.example.starwars.domain.usecase

import com.example.starwars.data.features.starwarspeople.remote.implement.StarWarsImplement
import com.example.starwars.domain.model.PeopleResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(
    private val repository: StarWarsImplement
) {

    suspend operator fun invoke(): Flow<List<PeopleResponse>> = repository.getAllPeople()
}