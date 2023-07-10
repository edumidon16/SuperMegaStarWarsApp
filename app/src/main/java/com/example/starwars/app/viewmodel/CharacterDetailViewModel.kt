package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.domain.usecase.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterSearcher = MutableStateFlow<ResourceState<*>>(ResourceState.Idle)

    val characterSearcher: StateFlow<ResourceState<*>>
        get() = _characterSearcher

    fun searchByName(query: String) {
        _characterSearcher.update { ResourceState.Loading("") }
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterDetailUseCase(query).collectLatest { character ->
                _characterSearcher.update {
                    if (character.isNotEmpty()) {
                        ResourceState.Success(character)
                    } else {
                        ResourceState.Error("Error")
                    }
                }
            }
        }
    }
}