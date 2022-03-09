package com.example.pixabay.data.remote

import com.example.pixabay.BuildConfig.API_KEY
import com.example.pixabay.data.local.entity.PixabayResponse
import com.example.pixabay.utils.Constants.Companion.DEFAULT_QUERY
import com.example.pixabay.utils.Constants.Companion.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET(END_POINT)
    suspend fun searchImages(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") searchQuery: String
    ): Response<PixabayResponse>

    @GET(END_POINT)
    suspend fun fetchImages(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") searchQuery: String = DEFAULT_QUERY
    ) : PixabayResponse
}