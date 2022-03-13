package com.example.pixabay.data.remote

import com.example.pixabay.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("?key=$API_KEY")
    suspend fun fetchImages(
        @Query("q") query: String?
    ) : PixabayResponse
}