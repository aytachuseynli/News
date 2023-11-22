package com.aytachuseynli.news.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private const val API_KEY = "8cf0add5e81745f8a2883238059d91c5"

        fun getClient(baseUrl:String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
