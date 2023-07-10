package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(): Flow<List<PeopleResponse>> = repository.getAllPeople()
}