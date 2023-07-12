package com.example.starwars.data.features.starwarspeople.mappers

import com.example.starwars.data.features.starwarspeople.remote.model.RemoteCharacterDetailResponse
import com.example.starwars.domain.model.ModelCharacter
import com.example.starwars.domain.model.ModelCharacterDetailResponse


fun RemoteCharacterDetailResponse.toDomain(): ModelCharacterDetailResponse =
    ModelCharacterDetailResponse(
        //domain == data
        // https://jsonviewer.stack.hu/
        // List [ ] .map { it.toDomain() },
        // Array { } .toDomain(),
        result = this.result?.map { it.toDomain() },
    )

fun RemoteCharacterDetailResponse.CharacteDetail.toDomain(): ModelCharacter =
    ModelCharacter(
        name = this.properties?.name,
        height = this.properties?.height,
        mass = this.properties?.mass,
        gender = this.properties?.gender
    )

fun RemoteCharacterDetailResponse.CharacteDetail.Detail.toDomain(): ModelCharacter = ModelCharacter(
    name = this.name,
    height = this.height,
    mass = this.mass,
    gender = this.gender
)

