package com.example.animeproject.domain

import com.example.animeproject.domain.response.AnimeResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("anime")
    fun getAllAnime(): Observable<AnimeResponse>
}