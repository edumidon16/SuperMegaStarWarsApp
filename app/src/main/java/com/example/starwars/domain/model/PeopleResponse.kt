package com.example.starwars.domain.model

import java.io.Serializable

data class PeopleResponse(
    val results: List<People>?,
) : Serializable

data class People(
    val id: String?,
    val name: String?
) : Serializable