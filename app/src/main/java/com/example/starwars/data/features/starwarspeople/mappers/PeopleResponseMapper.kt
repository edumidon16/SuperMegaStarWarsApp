package com.example.starwars.data.features.starwarspeople.mappers

import com.example.starwars.data.features.starwarspeople.remote.model.CharacterInfoStarWars
import com.example.starwars.domain.model.People

fun peopleResponseMapper(peopleResponse: CharacterInfoStarWars): People {
    return People(
        //domain == data
        id = peopleResponse.id,
        name = peopleResponse.name
    )
}