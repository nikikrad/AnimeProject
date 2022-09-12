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

    private var disposable = CompositeDisposable()

//    fun getAllAnime() {
//        disposable.add(apiService.getAllAnime()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("KEK", it.data.toString())
//            }, {
//
//            }))
//    }

//    fun getAllAnime(): Observable<AnimeResponse> {
//        return Observable.create{ observable ->
//            apiService.getAllAnime()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ list ->
//                    Log.d("KEK", list.toString())
//                    observable.onNext(list)
//                }, { throwable ->
//                    Log.e("BUG", throwable.toString())
//                })
//        }
//    }
    fun getApiService(): ApiService{
        return apiService
    }
}