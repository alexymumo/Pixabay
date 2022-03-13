package com.example.pixabay.data.local.repository

import com.example.pixabay.data.local.db.PixabayDb
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.data.remote.PixabayApi
import com.example.pixabay.utils.Resource
import com.example.pixabay.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    //private val pixabayDao: PixabayDao,
    private val pixabayApi: PixabayApi,
    private val pixabayDatabase: PixabayDb
    ): SafeApiRequest() {
        suspend fun fetchImages(name: String): Flow<Resource<List<Pixabay>>> = flow {
            emit(Resource.Loading())

            val pixabayImage = pixabayDatabase.pixabayDao().fetchImages(name)
            emit(Resource.Loading(data = pixabayImage))

            try {
                val data = pixabayApi.fetchImages(name)
                pixabayDatabase.pixabayDao().saveImage(data.hits)
                //pixabayDatabase.pixabayDao().deleteAll(data.hits.map { it.previewURL })

            }catch (e: HttpException) {
                emit(
                    Resource.Error(message = "oops something went wrong",
                        data = pixabayImage
                    )
                )

            }
            catch (e: IOException) {
                emit(
                    Resource.Error("Internet error", data = pixabayImage
                    )
                )
            }
            val newImage = pixabayDatabase.pixabayDao().fetchImages(name)
            emit(Resource.Success(newImage))
        }

}



