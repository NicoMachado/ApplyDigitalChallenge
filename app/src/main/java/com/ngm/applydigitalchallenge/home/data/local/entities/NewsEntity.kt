package com.ngm.applydigitalchallenge.home.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val author: String,
    val createdAt: String,
    val storyUrl: String?,
    val isDeleted: Boolean = false
)