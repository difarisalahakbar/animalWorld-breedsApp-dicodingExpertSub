package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Images
import kotlinx.coroutines.flow.Flow

interface BreedsUseCase {
    fun getAllBreeds(): Flow<Resource<List<Breeds>>>

    fun getSearchName(name: String): Flow<List<Breeds>>

    fun getAllImage(breedId: String, limit: Int): Flow<Resource<List<Images>>>

    fun getAllFavoriteBreeds(): Flow<List<Breeds>>

    fun setFavoriteBreeds(breeds: Breeds, state: Boolean)
}