package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.BreedsUseCase
import javax.inject.Inject
@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory @Inject constructor(private val breedsUseCase: BreedsUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            FavoriteViewModel(breedsUseCase) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}