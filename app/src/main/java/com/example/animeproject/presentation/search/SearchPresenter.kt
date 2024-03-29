package com.example.animeproject.presentation.search

import android.util.Log
import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.presentation.search.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class SearchPresenter(
    private val searchRepository: SearchRepository,
    private val searchView: SearchView
) : MvpPresenter<SearchView>() {

    private var disposable = CompositeDisposable()

    fun getMultByName(name: String, binding:FragmentSearchBinding) {

        disposable.add(
            searchRepository.getApiService().getMultByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchView.getMultByName(it, binding)
                }, {
                    Log.e("Error", it.localizedMessage.toString())
                })
        )
    }

    fun getMultByGenre(name: String, binding:FragmentSearchBinding) {

        disposable.add(
            searchRepository.getApiService().getMultByGenre(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchView.getMultByGenre(it, binding)
                }, {
                    Log.e("Error", it.localizedMessage.toString())
                })
        )
    }
}
