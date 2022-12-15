package com.example.core.domain.usecase

import android.content.Context
import com.example.core.data.Resource
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Images
import com.example.core.domain.repository.IBreedsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreedsInteractor @Inject constructor(private val breedsRepository: IBreedsRepository): BreedsUseCase {
    override fun getAllBreeds(context: Context): Flow<Resource<List<Breeds>>> {
        return breedsRepository.getAllBreeds(context)
    }

    override fun getSearchName(name: String): Flow<List<Breeds>> {
        return breedsRepository.getSearchName(name)
    }

    override fun getAllImage(breedId: String, limit: Int): Flow<Resource<List<Images>>>{
        return breedsRepository.getAllImage(breedId, limit)
    }

    override fun getAllFavoriteBreeds(): Flow<List<Breeds>> {
        return breedsRepository.getAllFavoriteBreeds()
    }

    override fun setFavoriteBreeds(breeds: Breeds, state: Boolean) {
        return breedsRepository.setFavoriteBreeds(breeds, state)
    }
}