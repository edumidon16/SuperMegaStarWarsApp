package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.domain.usecase.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
) : ViewModel() {

    private val _resourceState = MutableStateFlow<ResourceState>(ResourceState.loading)
    val resourceState: StateFlow<ResourceState> = _resourceState


}