package com.example.starwars.domain.model

data class PeopleResponse(
    val results: List<People?>?,
)

data class People(
    val id: String,
    val name: String
)