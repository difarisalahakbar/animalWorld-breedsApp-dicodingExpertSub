package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.BreedsDao
import com.example.core.data.source.local.room.BreedsDatabase
import com.example.core.data.source.local.room.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BreedsDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("animalworld".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            BreedsDatabase::class.java, "bred.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideBreedsDao(database: BreedsDatabase):BreedsDao = database.getBreedDao()

    @Provides
    fun provideImagesDao(database: BreedsDatabase):ImagesDao = database.getImagesDao()
}