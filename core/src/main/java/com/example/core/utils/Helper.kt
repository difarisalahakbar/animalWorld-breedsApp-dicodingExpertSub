package com.example.core.utils

import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.core.R
import com.example.core.data.source.local.entity.BreedsEntity
import com.example.core.data.source.local.entity.ImagesEntity
import com.example.core.data.source.remote.response.BreedResponseItem
import com.example.core.data.source.remote.response.ImageResponseItem
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Category
import com.example.core.domain.model.Images
import java.net.NetworkInterface

object Helper {
    fun mapResponseToEntities(input: List<BreedResponseItem>): List<BreedsEntity> =
        input.map {
            BreedsEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                urlImage = it.image?.url,
                origin = it.origin,
                temperament = it.temperament,
                isFavorite = false
            )
        }


    fun mapEntitiesToDomain(input: List<BreedsEntity>): List<Breeds> =
        input.map {
            Breeds(
                id = it.id,
                name = it.name,
                description = it.description,
                urlImage = it.urlImage,
                origin = it.origin,
                temperament = it.temperament,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Breeds) = BreedsEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        urlImage = input.urlImage,
        origin = input.origin,
        temperament = input.temperament,
        isFavorite = input.isFavorite

    )

    fun mapResponseImageToEntities(input: List<ImageResponseItem>): List<ImagesEntity> =
        input.map {
            ImagesEntity(
                id = it.id,
                urlImage = it.urlImage
            )
        }


    fun mapEntitiesImagesToDomain(input: List<ImagesEntity>): List<Images> =
        input.map {
            Images(
                id = it.id,
                urlImage = it.urlImage
            )
        }

    fun addDummyCategory(list: ArrayList<Category>) {
        list.add(Category("Cat", R.drawable.cats))
        list.add(Category("Dog", R.drawable.dog))
        list.add(Category("Bear", R.drawable.bear))
        list.add(Category("Eagle", R.drawable.eagle))
        list.add(Category("Tiger", R.drawable.tiger))
        list.add(Category("Monkey", R.drawable.monkey))
        list.add(Category("Cow", R.drawable.cow))
        list.add(Category("Other", R.drawable.other))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }




}