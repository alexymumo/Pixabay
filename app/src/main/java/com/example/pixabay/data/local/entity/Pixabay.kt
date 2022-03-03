package com.example.pixabay.data.local.entity

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "pixabay_table")
data class Pixabay(
    val id : Int,
    val type : String,
    val tags : String,
    val webformatURL : String,
    val views : Int,
    val downloads : Int,
    val comments : Int,
    val likes : Int,
    val user : String
): Serializable

/*
Serialization - Process of converting data used by an application to a format that
can be transferred over network and stored in database file
Deserialization - reading data from data source and converting it into a runtime object
*/
