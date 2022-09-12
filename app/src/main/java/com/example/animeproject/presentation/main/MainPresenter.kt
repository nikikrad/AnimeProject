package com.example.animeproject.presentation.main

import android.util.Log
import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.main.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository
): MvpPresenter<MainView>() {

//    fun getAllAnime(){
//        mainRepository.getAllAnime()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response ->
////                    Log.e("KEK", response.toString())
//
//            }, {
//                Log.e("KEK", it.localizedMessage!!)
//            })
//    }
    fun getAllAnime(): Observable<AnimeResponse> {
        return Observable.create{ observable ->
            mainRepository.getApiService().getAllAnime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    Log.d("KEK", list.toString())
                    observable.onNext(list)
                }, { throwable ->
                    Log.e("BUG", throwable.toString())
                })
        }
    }

    override fun onFirstViewAttach() {
//        getAllAnime()

    }
}