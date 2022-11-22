package com.example.capstones.di

import com.example.core.domain.usecase.BreedsInteractor
import com.example.core.domain.usecase.BreedsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideBreedsUseCase(breedsInteractor: BreedsInteractor): BreedsUseCase

}