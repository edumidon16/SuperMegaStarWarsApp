package com.example.starwars.data.features.starwarspeople.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePeopleResponse(
    @SerializedName("results")
    val results: List<CharacterInfoStarWars?>?,
)

data class CharacterInfoStarWars(
    @SerializedName("uid") val id: String?,
    @SerializedName("name") val name: String?
)