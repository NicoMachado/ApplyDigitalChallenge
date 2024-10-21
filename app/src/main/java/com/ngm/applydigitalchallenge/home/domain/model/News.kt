package com.ngm.applydigitalchallenge.home.domain.model

data class News (
    val id: Int,
    val title: String,
    val author: String,
    val createdAt: String,
    val storyUrl: String?,
    val isDeleted: Boolean = false
)