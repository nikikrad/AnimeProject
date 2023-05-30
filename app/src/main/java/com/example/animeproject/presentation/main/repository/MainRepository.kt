package com.example.animeproject.presentation.main.repository

import com.example.animeproject.domain.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService){

    fun getApiService(): ApiService{
        return apiService
    }
}