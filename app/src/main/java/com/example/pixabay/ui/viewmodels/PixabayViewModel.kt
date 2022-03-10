package com.example.pixabay.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pixabay.data.local.entity.PixabayResponse
import com.example.pixabay.data.local.repository.PixabayRepository
import com.example.pixabay.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(
    private val pixabayRepository: PixabayRepository
    ): ViewModel() {
    var images = pixabayRepository.fetchDb().asLiveData()
    private val searchedImages : MutableLiveData<Resource<PixabayResponse>> = MutableLiveData()
    var searched: MutableLiveData<Resource<PixabayResponse>> = searchedImages

    fun getImages(searchQuery: String) = viewModelScope.launch {
        searchedImages.postValue(Resource.Loading())
        val response = pixabayRepository.searchImages(searchQuery)
        searchedImages.postValue(handleImageResponse(response))
    }
    private fun handleImageResponse(response: Response<PixabayResponse>): Resource<PixabayResponse> {
        if (response.isSuccessful) {
            response.body()?.let { imageResponse ->
                return Resource.Success(imageResponse)
            }
        }
        return Resource.Error(response.message())
    }
}