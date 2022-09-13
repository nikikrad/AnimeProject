package com.example.animeproject.domain

import com.example.animeproject.domain.response.AnimeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("anime")
    fun getAllAnime(): Observable<AnimeResponse>

    @GET("anime")
    fun getAnimeByName(
        @Query("filter[text]") nameAnime: String
//        @Path("anime") nameAnime: Char
    ): Observable<AnimeResponse>
}