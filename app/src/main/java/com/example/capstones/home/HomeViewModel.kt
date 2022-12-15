package com.example.capstones.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.BreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val breedsUseCase: BreedsUseCase) : ViewModel() {

    fun getAllBreeds(context: Context) = breedsUseCase.getAllBreeds(context).asLiveData()

}