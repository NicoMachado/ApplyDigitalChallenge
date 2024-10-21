package com.ngm.applydigitalchallenge.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ngm.applydigitalchallenge.home.data.local.entities.NewsEntity

@Dao
interface HackerNewsDao {
    @Query("SELECT * FROM NewsEntity WHERE isDeleted = 0")
    suspend fun getLatestNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNew(newsEntity: NewsEntity)

    @Transaction
    @Query("UPDATE NewsEntity SET isDeleted = 1 WHERE id = :id")
    suspend fun deleteNews(id: Int)

}