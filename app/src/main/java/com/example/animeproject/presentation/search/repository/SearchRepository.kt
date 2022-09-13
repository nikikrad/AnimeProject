package com.example.animeproject.presentation.search.repository

import com.example.animeproject.domain.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {
    fun getApiService(): ApiService {
        return apiService
    }
}