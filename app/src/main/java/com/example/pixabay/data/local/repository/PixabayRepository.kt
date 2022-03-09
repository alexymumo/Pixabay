package com.example.pixabay.data.local.repository

import com.example.pixabay.BuildConfig.API_KEY
import com.example.pixabay.data.local.dao.PixabayDao
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.data.local.entity.PixabayResponse
import com.example.pixabay.data.remote.PixabayApi
import com.example.pixabay.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val pixabayDao: PixabayDao,
    private val pixabayApi: PixabayApi
    ): SafeApiRequest() {

    fun fetchDb(): Flow<List<Pixabay>> {
        return pixabayDao.fetchAllImages()
    }
    suspend fun saveImage(pixabay: List<Pixabay>){
        pixabayDao.saveImage(pixabay)
    }

    suspend fun deleteImage() {
        pixabayDao.deleteAll()
    }
    suspend fun searchImages(searchQuery: String): Response<PixabayResponse> {
        return pixabayApi.searchImages(API_KEY, searchQuery)
    }

    suspend fun fetchImages(searchQuery: String)  = safeApiCall {
        pixabayApi.fetchImages(API_KEY, searchQuery)
    }
}