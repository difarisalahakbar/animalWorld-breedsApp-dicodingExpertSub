package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val id: String,

    val urlImage: String
) : Parcelable