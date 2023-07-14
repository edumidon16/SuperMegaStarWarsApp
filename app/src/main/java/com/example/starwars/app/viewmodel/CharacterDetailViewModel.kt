package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.app.common.states.ResourceState.Idle
import com.example.starwars.app.common.states.ResourceState.Success
import com.example.starwars.domain.model.ModelCharacter
import com.example.starwars.domain.model.ModelCharacterDetailResponse
import com.example.starwars.domain.usecase.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


typealias CharacterDetailState = ResourceState<List<ModelCharacterDetailResponse>>

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    val characterSearcher: MutableStateFlow<CharacterDetailState> = MutableStateFlow(Idle())
    private var detailList: List<ModelCharacter> = emptyList()

    fun searchByName(query: String) {
        characterSearcher.update { ResourceState.Loading() }
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterDetailUseCase(query).collectLatest { character ->
                characterSearcher.update {
                    if (character.isNotEmpty()) {
                        Success(character)
                    } else {
                        ResourceState.Error(character)
                    }
                }
            }
        }
    }

    fun getDetailCharacter(name: String?, callback: (List<ModelCharacter>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name != null) {
                getCharacterDetailUseCase(name).collectLatest { character ->
                    if (character.isNotEmpty()) {
                        val detailList = character.first().result.orEmpty()
                        callback(detailList)
                    } else {
                    }

                }
            }
        }
    }

}