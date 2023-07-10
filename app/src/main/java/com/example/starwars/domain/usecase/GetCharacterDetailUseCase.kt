package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.ModelCharacterDetailResponse
import com.example.starwars.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: MyRepository

) {
    suspend operator fun invoke(name: String): Flow<List<ModelCharacterDetailResponse>> =
        repository.getDetailCharacter(name)
}