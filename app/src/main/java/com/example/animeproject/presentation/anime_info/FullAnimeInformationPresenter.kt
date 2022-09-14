package com.example.animeproject.presentation.anime_info

import android.util.Log
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.domain.response.SingleAnimeResponse
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.main.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class FullAnimeInformationPresenter @Inject constructor(
    private val fullAnimeInformationRepository: FullAnimeInformationRepository
): MvpPresenter<MainView>() {

    private val disposable = CompositeDisposable()

    fun getAnimeById(id: Int): Observable<SingleAnimeResponse>{
        return Observable.create{ observer ->
            disposable.add(fullAnimeInformationRepository.getApiService().getAnimeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    observer.onNext(it)
                },{
                    Log.e("Error", it.localizedMessage)
                })
            )
        }

    }

}