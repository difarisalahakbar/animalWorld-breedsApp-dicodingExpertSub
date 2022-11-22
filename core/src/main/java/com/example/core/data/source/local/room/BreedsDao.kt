package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.BreedsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): Flow<List<BreedsEntity>>

    @Query("SELECT * FROM breeds WHERE isFavorite = 1")
    fun getAllFavoriteBreeds(): Flow<List<BreedsEntity>>

    @Query("SELECT * FROM breeds WHERE name LIKE :name")
    fun getSearchName(name: String): Flow<List<BreedsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreeds(list: List<BreedsEntity>)

    @Update
    fun updateFavorite(breedsEntity: BreedsEntity)

}