package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.BreedsEntity
import com.example.core.data.source.local.entity.ImagesEntity

@Database(entities = [BreedsEntity::class, ImagesEntity::class], version = 1, exportSchema = false)
abstract class BreedsDatabase : RoomDatabase() {

    abstract fun getBreedDao(): BreedsDao

    abstract fun getImagesDao(): ImagesDao

}