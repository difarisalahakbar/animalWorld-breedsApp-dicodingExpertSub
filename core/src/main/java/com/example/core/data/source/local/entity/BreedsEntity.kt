package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedsEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var urlImage: String?,

    @ColumnInfo(name = "origin")
    var origin: String,

    @ColumnInfo(name = "temperament")
    var temperament: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)