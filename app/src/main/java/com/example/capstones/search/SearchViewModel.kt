package com.example.capstones.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.BreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val breedsUseCase: BreedsUseCase) : ViewModel() {

    fun getSearchName(name: String) = breedsUseCase.getSearchName(name).asLiveData()

}