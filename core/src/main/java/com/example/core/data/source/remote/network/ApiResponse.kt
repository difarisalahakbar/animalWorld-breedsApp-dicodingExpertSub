package com.example.core.data.source.remote.network

sealed class ApiResponse<out T> {
    data class Success<out R>(val data: R): ApiResponse<R>()
    data class Error(val errorMessage: String): ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}