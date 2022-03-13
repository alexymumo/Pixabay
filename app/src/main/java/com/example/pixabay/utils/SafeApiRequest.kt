package com.example.pixabay.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class SafeApiRequest {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        Resource.Error(throwable.localizedMessage, null)

                    }
                }
            }
        }
    }
}