package com.aytachuseynli.news.data.remote.retrofit

import androidx.room.Dao
import com.aytachuseynli.news.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Dao
interface NewsDao {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("language") country: String="en",
        @Query("apiKey") apiKey: String = "8cf0add5e81745f8a2883238059d91c5"
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchNews(
        @Query("language") lang: String,
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>


}