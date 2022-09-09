package com.example.animeproject.presentation.main.repository

import android.util.Log
import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.response.AnimeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService){

    fun getAllAnime(): Observable<ArrayList<AnimeResponse>> {
        return Observable.create{ observable ->
            apiService.getAllAnime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    observable.onNext(response)
                }, { throwable ->
                    Log.e("KEK", throwable.toString())
                })
        }
    }
}