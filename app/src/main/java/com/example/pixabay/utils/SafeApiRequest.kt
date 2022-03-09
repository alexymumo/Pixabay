package com.example.pixabay.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class SafeApiRequest {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Any {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable){

            }
        }
    }
}