package com.ngm.applydigitalchallenge.home.domain.repository

import com.ngm.applydigitalchallenge.home.domain.model.News

interface HackerNewsRepository{
    suspend fun getNews(): List<News>
    suspend fun deleteNews(id: Int)
}