package com.aytachuseynli.news.data.remote.retrofit

object ApiUtils {

    private const val BASE_URL = "https://newsapi.org/v2/"

    fun getNewsDao() : NewsDao {
        return RetrofitClient.getClient(BASE_URL).create(NewsDao::class.java)
    }


}