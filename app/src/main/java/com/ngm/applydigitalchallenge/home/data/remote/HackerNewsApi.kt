package com.ngm.applydigitalchallenge.home.data.remote

import com.ngm.applydigitalchallenge.home.data.remote.dto.HackerNewsResponse
import retrofit2.http.GET

interface HackerNewsApi {
    companion object {
        const val BASE_URL = "https://hn.algolia.com/api/v1/"
    }

    @GET("search_by_date?query=mobile")
    suspend fun getTopArticles(): HackerNewsResponse

}
