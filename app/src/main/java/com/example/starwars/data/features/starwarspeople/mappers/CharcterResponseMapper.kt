package com.example.starwars.data.features.starwarspeople.mappers

import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars
import com.example.starwars.data.features.starwarspeople.remote.model.RemotePeopleResponse
import com.example.starwars.domain.model.People
import com.example.starwars.domain.model.PeopleResponse

// fun data <==> domain
fun RemotePeopleResponse.toDomain(): PeopleResponse = PeopleResponse(
    //domain == data
    // https://jsonviewer.stack.hu/
    // List [ ] .map { it.toDomain() },
    // Array { } .toDomain(),
    results = this.results?.map { it?.toDomain() ?: People("", "") }
)

fun CharacterInfoStarWars.toDomain(): People = People(
    id = this.id,
    name = this.name
)