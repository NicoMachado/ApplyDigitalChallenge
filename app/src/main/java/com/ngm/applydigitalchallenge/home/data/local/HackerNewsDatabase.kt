package com.ngm.applydigitalchallenge.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ngm.applydigitalchallenge.home.data.local.entities.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1
)
abstract class HackerNewsDatabase: RoomDatabase() {
    abstract val newsDao: HackerNewsDao
}