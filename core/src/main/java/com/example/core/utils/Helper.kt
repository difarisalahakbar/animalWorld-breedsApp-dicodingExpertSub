package com.example.core.utils

import com.example.core.R
import com.example.core.data.source.local.entity.BreedsEntity
import com.example.core.data.source.local.entity.ImagesEntity
import com.example.core.data.source.remote.response.BreedResponseItem
import com.example.core.data.source.remote.response.ImageResponseItem
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Category
import com.example.core.domain.model.Images

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

    fun addDummyCategory(list: ArrayList<Category>){
        list.add(Category("Cat", R.drawable.cats))
        list.add(Category("Dog", R.drawable.dog))
        list.add(Category("Bear", R.drawable.bear))
        list.add(Category("Eagle", R.drawable.eagle))
        list.add(Category("Tiger", R.drawable.tiger))
        list.add(Category("Monkey", R.drawable.monkey))
        list.add(Category("Cow", R.drawable.cow))
        list.add(Category("Other", R.drawable.other))
    }

}