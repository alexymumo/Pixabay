package com.example.pixabay.data.remote

import com.example.pixabay.data.local.entity.Pixabay
import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    @SerializedName("hits")
    val hits: List<Pixabay>
)

//25973111-24329f9f2d4e8aac3285bdcee
