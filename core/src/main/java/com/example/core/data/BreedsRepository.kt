package com.example.core.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.BreedResponseItem
import com.example.core.data.source.remote.response.ImageResponseItem
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Images
import com.example.core.domain.repository.IBreedsRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.Helper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedsRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IBreedsRepository {

    override fun getAllBreeds(context: Context): Flow<Resource<List<Breeds>>> =
        object : NetworkBoundResource<List<Breeds>, List<BreedResponseItem>>() {
            override fun loadFromDB(): Flow<List<Breeds>> {
                return localDataSource.getAllBreeds().map { Helper.mapEntitiesToDomain(it) }
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun shouldFetch(data: List<Breeds>?): Boolean {
                return if(Helper.isNetworkConnected(context)){
                    true
                } else{
                    data == null || data.isEmpty()
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<BreedResponseItem>>> {
                return remoteDataSource.getAllBreeds()
            }

            override suspend fun saveCallResult(data: List<BreedResponseItem>) {
                val data = Helper.mapResponseToEntities(data)
                localDataSource.insertAllBreeds(data)
            }

        }.asFlow()

    override fun getAllImage(breedId: String, limit: Int): Flow<Resource<List<Images>>> =
        object : NetworkBoundResource<List<Images>, List<ImageResponseItem>>() {
            override fun loadFromDB(): Flow<List<Images>> {
                return localDataSource.getAllImages()
                    .map { Helper.mapEntitiesImagesToDomain(it) }
            }

            override fun shouldFetch(data: List<Images>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ImageResponseItem>>> {
                return remoteDataSource.getAllImage(breedId, limit)
            }

            override suspend fun saveCallResult(data: List<ImageResponseItem>) {
                val data = Helper.mapResponseImageToEntities(data)
                localDataSource.deleteAllImages()
                localDataSource.insertAllImages(data)
            }

        }.asFlow()

    override fun getAllFavoriteBreeds(): Flow<List<Breeds>> {
        return localDataSource.getAllFavoriteBreeds().map {
            Helper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchName(name: String): Flow<List<Breeds>> {
        return localDataSource.getSearchName("%$name%").map {
            Helper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteBreeds(breeds: Breeds, state: Boolean) {
        val breedsEntity = Helper.mapDomainToEntity(breeds)
        appExecutors.diskIO().execute { localDataSource.setFavoriteBreeds(breedsEntity, state) }
    }
}