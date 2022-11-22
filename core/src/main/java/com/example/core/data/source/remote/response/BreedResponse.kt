package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Image(

    @field:SerializedName("url")
    val url: String

)

data class BreedResponseItem(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image")
    val image: Image?,

    @field:SerializedName("origin")
    val origin: String,

    @field:SerializedName("temperament")
    val temperament: String,

)
