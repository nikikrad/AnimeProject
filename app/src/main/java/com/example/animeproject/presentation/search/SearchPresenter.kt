package com.example.animeproject.presentation.search

import android.util.Log
import com.example.animeproject.databinding.FragmentSearchBinding
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

class SearchPresenter(
    private val searchRepository: SearchRepository,
    private val searchView: SearchView
) : MvpPresenter<SearchView>() {

    private var disposable = CompositeDisposable()

    fun getAnimeByName(name: String, binding:FragmentSearchBinding) {

        disposable.add(
            searchRepository.getApiService().getAnimeByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchView.getAnimeByName(it, binding)
                }, {
                    Log.e("Error", it.localizedMessage.toString())
                })
        )
    }
}
