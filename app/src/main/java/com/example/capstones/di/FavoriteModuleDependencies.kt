package com.example.capstones.di

import com.example.core.domain.usecase.BreedsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun breedsUseCase(): BreedsUseCase

}