package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageResponseItem (

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("url")
    val urlImage: String

)
