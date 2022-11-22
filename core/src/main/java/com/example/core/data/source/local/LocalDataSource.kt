package com.example.core.data.source.local

import com.example.core.data.source.local.entity.BreedsEntity
import com.example.core.data.source.local.entity.ImagesEntity
import com.example.core.data.source.local.room.BreedsDao
import com.example.core.data.source.local.room.ImagesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val breedsDao: BreedsDao,
    private val imagesDao: ImagesDao
) {

    fun getAllBreeds(): Flow<List<BreedsEntity>> = breedsDao.getAllBreeds()

    fun getAllFavoriteBreeds(): Flow<List<BreedsEntity>> = breedsDao.getAllFavoriteBreeds()

    fun getSearchName(name: String): Flow<List<BreedsEntity>> = breedsDao.getSearchName(name)

    fun getAllImages(): Flow<List<ImagesEntity>> = imagesDao.getAllImage()

    fun setFavoriteBreeds(breedsEntity: BreedsEntity, state: Boolean) {
        breedsEntity.isFavorite = state
        breedsDao.updateFavorite(breedsEntity)
    }

    suspend fun insertAllBreeds(list: List<BreedsEntity>) = breedsDao.insertAllBreeds(list)

    suspend fun insertAllImages(list: List<ImagesEntity>) = imagesDao.insertAllImage(list)

    suspend fun deleteAllImages() = imagesDao.deleteAll()
}