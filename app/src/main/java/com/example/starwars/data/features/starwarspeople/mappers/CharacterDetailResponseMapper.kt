package com.example.starwars.data.features.starwarspeople.mappers

import com.example.starwars.data.features.starwarspeople.remote.model.RemoteCharacterDetailResponse
import com.example.starwars.domain.model.ModelCharacter
import com.example.starwars.domain.model.ModelCharacterDetail
import com.example.starwars.domain.model.ModelCharacterDetailResponse


fun RemoteCharacterDetailResponse.toDomain(): ModelCharacterDetailResponse =
    ModelCharacterDetailResponse(
        //domain == data
        // https://jsonviewer.stack.hu/
        // List [ ] .map { it.toDomain() },
        // Array { } .toDomain(),
        result = this.result?.map { it.toDomain() },
    )

fun RemoteCharacterDetailResponse.CharacteDetail.toDomain(): ModelCharacterDetail =
    ModelCharacterDetail(
        properties = this.properties?.toDomain(),
    )

fun RemoteCharacterDetailResponse.CharacteDetail.Detail.toDomain(): ModelCharacter = ModelCharacter(
    name = this.name,
    height = this.height,
    mass = this.mass,
    gender = this.gender
)

