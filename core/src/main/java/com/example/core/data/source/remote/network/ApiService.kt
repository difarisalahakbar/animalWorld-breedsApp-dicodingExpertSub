package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.BreedResponseItem
import com.example.core.data.source.remote.response.ImageResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("breeds")
    suspend fun getListBreeds(): List<BreedResponseItem>

    @GET("images/search")
    suspend fun getListPhoto(
        @Query("breed_id") breedId: String,
        @Query("limit") limit: Int
    ): List<ImageResponseItem>

}