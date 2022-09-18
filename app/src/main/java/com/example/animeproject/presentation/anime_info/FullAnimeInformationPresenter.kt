package com.example.animeproject.presentation.anime_info

import android.util.Log
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.domain.response.SingleAnimeResponse
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.main.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

//@InjectViewState
class FullAnimeInformationPresenter(
    private val fullAnimeInformationRepository: FullAnimeInformationRepository,
    private val fullAnimeInformationView: FullAnimeInformationView
) : MvpPresenter<FullAnimeInformationView>() {

    private val disposable = CompositeDisposable()

    fun getAnimeById(id: Int) {
        disposable.add(
            fullAnimeInformationRepository.getApiService().getAnimeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    fullAnimeInformationView.getAnimeById(it)
//                    observer.onNext(it)
                }, {
                    Log.e("Error", it.localizedMessage)
                })
        )
    }

}

