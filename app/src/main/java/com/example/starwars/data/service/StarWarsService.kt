package com.example.starwars.data.service

import com.example.starwars.data.constants.Constants.Companion.BASE_URL
import com.example.starwars.data.constants.Constants.Companion.PEOPLE
import com.example.starwars.data.features.starwarspeople.remote.model.RemoteCharacterDetailResponse
import com.example.starwars.data.features.starwarspeople.remote.model.RemotePeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsService {

    @GET(BASE_URL + PEOPLE)
    suspend fun getAllPeople(): Response<RemotePeopleResponse>

    @GET(BASE_URL + PEOPLE)
    suspend fun getDetailCharacter(@Query("name") characterName: String): Response<RemoteCharacterDetailResponse>

}