package com.example.starwars.app.common.states

sealed class ResourceState {
    object loading : ResourceState()
    data class Success(val name: String) : ResourceState()
    data class Error(val msg: String) : ResourceState()
}