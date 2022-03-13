package com.example.pixabay.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.data.local.repository.PixabayRepository
import com.example.pixabay.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(
    private val pixabayRepository: PixabayRepository
    ): ViewModel() {

    private val _searchQuery = MutableLiveData("shoe")
    val searchQuery : LiveData<String> = _searchQuery

    suspend fun getImages(query: String): Flow<Resource<List<Pixabay>>> {
        _searchQuery.value = query
        return pixabayRepository.fetchImages(query)
    }
}