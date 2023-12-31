package com.example.starwars.domain.model

import java.io.Serializable


data class ModelCharacterDetailResponse(
    val result: List<ModelCharacter>?,
) : Serializable

data class ModelCharacter(
    val name: String?,
    val height: String?,
    val mass: String?,
    val gender: String?
) : Serializable
