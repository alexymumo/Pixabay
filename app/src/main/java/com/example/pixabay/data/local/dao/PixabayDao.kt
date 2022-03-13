package com.example.pixabay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pixabay.data.local.entity.Pixabay
import kotlinx.coroutines.flow.Flow

@Dao
interface PixabayDao {

    @Query("SELECT * FROM pixabay_table")
    fun fetchAllImages(): Flow<List<Pixabay>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveImage(pixabay: List<Pixabay>)

    @Query("DELETE FROM pixabay_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM pixabay_table WHERE tags OR previewURL OR pageURL LIKE '%' || :query || '%'")
    suspend fun fetchImages(query: String?): List<Pixabay>
}