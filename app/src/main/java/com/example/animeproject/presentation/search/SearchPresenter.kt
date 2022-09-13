package com.example.animeproject.presentation.search

import android.util.Log
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.main.MainView
import com.example.animeproject.presentation.search.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val searchRepository: SearchRepository
): MvpPresenter<MainView>(){
    private var disposable = CompositeDisposable()

    fun getAnimeByName(name: String): Observable<AnimeResponse> {
        return Observable.create { observer ->
            disposable.add(searchRepository.getApiService().getAnimeByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Anime", it.data.toString())
                    observer.onNext(it)
                }, {
                    Log.e("Error", it.localizedMessage.toString())
                })
            )
        }
    }
}