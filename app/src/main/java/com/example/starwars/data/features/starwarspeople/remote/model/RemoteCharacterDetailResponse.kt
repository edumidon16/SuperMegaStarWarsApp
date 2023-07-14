package com.example.starwars.data.features.starwarspeople.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteCharacterDetailResponse(
    @SerializedName("result")
    val result: List<CharacteDetail>?
) {
    data class CharacteDetail(
        @SerializedName("properties")
        val properties: Detail?,
    ) {
        data class Detail(
            @SerializedName("gender")
            val gender: String?,
            @SerializedName("height")
            val height: String?,
            @SerializedName("mass")
            val mass: String?,
            @SerializedName("name")
            val name: String?,
        )
    }
}