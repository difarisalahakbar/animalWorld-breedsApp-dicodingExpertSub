package com.example.core.di

import com.example.core.data.BreedsRepository
import com.example.core.domain.repository.IBreedsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(breedsRepository: BreedsRepository): IBreedsRepository
}