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
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(private val pixabayRepository: PixabayRepository): ViewModel() {

    var images = pixabayRepository.fetchDb().asLiveData()
    private val _imageResponse : MutableLiveData<Resource<PixabayResponse>> = MutableLiveData()
    val imageResponse : MutableLiveData<Resource<PixabayResponse>>
    get() = _imageResponse
    //var  searched: MutableLiveData<Resource<PixabayResponse>> = _imageResponse

    fun getImages(searchQuery: String) = viewModelScope.launch {
        _imageResponse.value = (Resource.Loading)
        _imageResponse.value = pixabayRepository.fetchImages(searchQuery)
      //  searchedImages.postValue(handleImageResponse(response))

    }

}