package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.domain.usecase.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
) : ViewModel() {

    private val _characterList = MutableStateFlow<ResourceState<*>>(ResourceState.Idle)

    val characterList: StateFlow<ResourceState<*>>
        get() = _characterList

    fun loadCharacters() {
        _characterList.update { ResourceState.Loading("") }
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleListUseCase().collectLatest { character ->
                _characterList.update {
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
