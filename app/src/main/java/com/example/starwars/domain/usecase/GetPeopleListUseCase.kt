package com.example.starwars.domain.usecase

import com.example.starwars.data.features.starwarspeople.remote.implement.StarWarsImplement
import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(
    private val repository: StarWarsImplement
) {

    suspend operator fun invoke(): List<CharacterInfoStarWars> = repository.getAllPeople()
}