package com.example.capstones.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Breeds
import com.example.core.domain.usecase.BreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val breedsUseCase: BreedsUseCase) : ViewModel() {

    fun setFavorite(breeds: Breeds, state: Boolean) = breedsUseCase.setFavoriteBreeds(breeds, state)

    fun getAllImage(breedId: String) = breedsUseCase.getAllImage(breedId, 2).asLiveData()

}