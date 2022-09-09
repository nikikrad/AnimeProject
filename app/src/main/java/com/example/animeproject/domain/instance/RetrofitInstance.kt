package com.example.animeproject.domain.instance

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitInstance {

    companion object {

        private const val URL = "https://kitsu.io/api/edge/"

        fun getRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient()
                .newBuilder()
                .followRedirects(true)
                .build()

            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}