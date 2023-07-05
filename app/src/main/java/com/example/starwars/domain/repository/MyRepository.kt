package com.example.starwars.domain.repository

import com.example.starwars.domain.model.PeopleResponse
import kotlinx.coroutines.flow.Flow

interface MyRepository {

    suspend fun getAllPeople(): Flow<List<PeopleResponse>>
}