package com.example.core.domain.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breeds(
    val id: String,

    val name: String,

    val description: String,

    val urlImage: String?,

    val origin: String,

    val temperament: String,

    val isFavorite: Boolean = false
) : Parcelable