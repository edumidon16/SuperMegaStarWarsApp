package com.example.starwars.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.starwars.domain.usecase.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
) : ViewModel() {


}