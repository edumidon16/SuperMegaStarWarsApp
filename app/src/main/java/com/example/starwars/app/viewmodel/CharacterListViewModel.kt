package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.domain.model.People
import com.example.starwars.domain.model.PeopleResponse
import com.example.starwars.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias PeopleDetailState = ResourceState<List<PeopleResponse>>

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getPeopleListUseCase: GetCharacterListUseCase
) : ViewModel() {

    val characterList = MutableStateFlow<PeopleDetailState>(ResourceState.Idle())

    var currentCharacterList: List<People> = emptyList()
    var listOfCharacterRemove: List<People> = emptyList()
    var listOfFavorites: List<People> = emptyList()


    fun loadCharacters() {
        characterList.update { ResourceState.Loading() }
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleListUseCase().collectLatest { characters ->
                characterList.update {
                    if (characters.isNotEmpty()) {
                        ResourceState.Success(characters)
                    } else {
                        ResourceState.Error(characters)
                    }
                }
            }
        }
    }

    fun addNewRemoveCharacter(people: People?) {
        if (listOfCharacterRemove.isNotEmpty()) {
            listOfCharacterRemove.first().let {
                currentCharacterList = currentCharacterList.minus(it)
            }
        }
        if (people != null) {
            listOfCharacterRemove = listOfCharacterRemove.plus(people)
        }
        if (people != null) {
            currentCharacterList = currentCharacterList.minus(people)
        }
    }

    fun addNewCharacterToFavorites(people: People?) {
        if (people != null) {
            listOfFavorites = listOfFavorites.plus(people)
        }
    }
}
