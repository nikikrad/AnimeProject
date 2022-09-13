package com.example.animeproject.presentation.main

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.main.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository
) : MvpPresenter<MainView>() {

    private var disposable = CompositeDisposable()

    fun getDefaultAnime(): Observable<AnimeResponse> {
        return Observable.create { observable ->
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

    fun getAnimeByName(): Observable<AnimeResponse> {
        val rand = (0..26).random()
        val alf = "qwertyuiopasdfghjklzxcvbnm"
        return Observable.create { observer ->
            disposable.add(mainRepository.getApiService().getAnimeByName(alf[rand].toString())
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

    override fun onFirstViewAttach() {
//        getAllAnime()

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}