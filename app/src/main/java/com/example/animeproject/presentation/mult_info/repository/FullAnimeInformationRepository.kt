package com.example.animeproject.presentation.mult_info.repository

import com.example.animeproject.domain.ApiService
import javax.inject.Inject

class FullAnimeInformationRepository @Inject constructor(
    private val apiService: ApiService
){
    fun getApiService(): ApiService{
        return apiService
    }
}