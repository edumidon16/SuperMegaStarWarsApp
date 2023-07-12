package com.example.starwars.app.common.states

sealed class ResourceState<T> {
    class Idle<T> : ResourceState<T>()
    class Loading<T> : ResourceState<T>()
    data class Error<Throwable>(val throwable: Throwable) : ResourceState<Throwable>()
    data class Success<T>(val data: T) : ResourceState<T>()
}
