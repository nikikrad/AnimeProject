package com.example.animeproject.domain

import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.domain.response.SingleAnimeResponse
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
    ): Observable<AnimeResponse>

    @GET("anime")
    fun getAnimeById(
        @Query("filter[id]") idAnime: Int
    ): Observable<AnimeResponse>

    @GET("anime?page[limit]=20")
    fun getAnime(
        @Query("page[offset]") page: Int
    ):Observable<AnimeResponse>
}