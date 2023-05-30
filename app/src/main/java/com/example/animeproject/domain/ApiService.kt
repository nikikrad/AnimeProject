package com.example.animeproject.domain

import com.example.animeproject.domain.response.MultResponse
import com.example.animeproject.presentation.search.filter.Filter
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("anime")
    fun getMultByName(
        @Query("filter[text]") name: String
    ): Observable<MultResponse>

    @GET("anime")
    fun getMultByGenre(
        @Query("filter[text]") name: String ,
        @Query("filter[categories]") filter: String = Filter.getActiveGenreLikeText()
    ): Observable<MultResponse>

    @GET("anime")
    fun getMultById(
        @Query("filter[id]") id: Int
    ): Observable<MultResponse>

    @GET("anime?page[limit]=20")
    fun getMult(
        @Query("page[offset]") page: Int
    ):Observable<MultResponse>
}