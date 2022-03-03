package com.example.pixabay.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pixabay.data.local.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel
@Inject constructor(private val pixabayRepository: PixabayRepository): ViewModel()
{


}