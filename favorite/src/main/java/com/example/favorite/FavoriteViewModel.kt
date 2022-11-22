package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.BreedsUseCase

class FavoriteViewModel(private val breedsUseCase: BreedsUseCase) : ViewModel() {

    fun getAllFavoriteBreeds() = breedsUseCase.getAllFavoriteBreeds().asLiveData()

}