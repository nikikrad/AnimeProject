package com.example.animeproject.presentation.main.repository

import android.util.Log
import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.response.AnimeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService){

    fun getApiService(): ApiService{
        return apiService
    }
}