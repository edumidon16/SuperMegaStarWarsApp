package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.usecase.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias PeopleDetailState = ResourceState<List<PeopleResponse>>

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
) : ViewModel() {

    val characterList = MutableStateFlow<PeopleDetailState>(ResourceState.Idle())


    fun loadCharacters() {
        characterList.update { ResourceState.Loading() }
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleListUseCase().collectLatest { character ->
                characterList.update {
                    if (character.isNotEmpty()) {
                        ResourceState.Success(character)
                    } else {
                        ResourceState.Error(character)
                    }
                }
            }
        }
    }
}
