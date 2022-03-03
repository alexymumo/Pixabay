package com.example.pixabay.data.local.repository

import com.example.pixabay.data.local.dao.PixabayDao
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.data.remote.PixabayApi
import com.example.pixabay.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
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
    suspend fun fetchImages()  = safeApiCall {
        pixabayApi.fetchImages()
    }
}