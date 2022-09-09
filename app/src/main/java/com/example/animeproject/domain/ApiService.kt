package com.example.animeproject.domain

import com.example.animeproject.domain.response.AnimeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("anime")
    fun getAllAnime(): Observable<ArrayList<AnimeResponse>>
}