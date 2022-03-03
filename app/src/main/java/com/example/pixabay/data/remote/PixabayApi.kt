package com.example.pixabay.data.remote

import com.example.pixabay.data.local.entity.PixabayResponse
import com.example.pixabay.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api")
    suspend fun fetchImages(
        @Query("key") apiKey: String = API_KEY
    ) : PixabayResponse
}