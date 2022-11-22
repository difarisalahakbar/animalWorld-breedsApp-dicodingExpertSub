package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.ImagesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Query("SELECT * FROM images")
    fun getAllImage(): Flow<List<ImagesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImage(list: List<ImagesEntity>)

    @Query("DELETE FROM images")
    suspend fun deleteAll()
}