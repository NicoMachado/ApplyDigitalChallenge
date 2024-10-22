package com.ngm.applydigitalchallenge.home.data

import com.ngm.applydigitalchallenge.home.data.local.HackerNewsDao
import com.ngm.applydigitalchallenge.home.data.local.entities.NewsEntity
import com.ngm.applydigitalchallenge.home.data.remote.HackerNewsApi
import com.ngm.applydigitalchallenge.home.data.remote.dto.HitDto
import com.ngm.applydigitalchallenge.home.domain.model.News
import com.ngm.applydigitalchallenge.home.domain.repository.HackerNewsRepository

class HackerNewsRepositoryImpl(
    private val api: HackerNewsApi,
    private val dao: HackerNewsDao
) : HackerNewsRepository {

    private var newsList = listOf<News>()

    override suspend fun getNews(): List<News> {

        val remoteNews = getNewsFromApi()
        remoteNews.map {
            println("Insertando: ${it.objectId}")
            dao.insertNew(toNewsEntity(it))
        }

        newsList = dao.getLatestNews().map {
                entity -> toNews(entity) }
        return newsList
    }

    private suspend fun getNewsFromApi() : List<HitDto> {
        return try {
            val response = api.getTopArticles()
            println("getNewsFromApi : ${response.hits.size}")
            response.hits
        } catch (e: Exception) {
            emptyList<HitDto>()
        }
    }

    override suspend fun deleteNews(id: Int) {
        newsList = newsList.filter { it.id != id  }
        dao.deleteNews(id)
    }

    //Por simplicidad lo dejo aca, pero debe estar en un mapper
    private fun toNewsEntity(hitDto: HitDto): NewsEntity {
        return NewsEntity(
            title = hitDto.storyTitle ?: hitDto.title ?: "No title",
            author = hitDto.author,
            createdAt = hitDto.createdAt,
            storyUrl = hitDto.storyUrl ?: hitDto.url,
            id = hitDto.objectId.toInt()
        )
    }

    private fun toNews(newsEntity: NewsEntity): News {
        return News(
            title = newsEntity.title,
            author = newsEntity.author,
            createdAt = newsEntity.createdAt,
            storyUrl = newsEntity.storyUrl,
            id = newsEntity.id
        )
    }

}