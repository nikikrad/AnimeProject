package com.example.animeproject.presentation.main

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.main.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter(
    private val mainRepository: MainRepository,
    private val mainView: MainView
) : MvpPresenter<MainView>() {

    private var disposable = CompositeDisposable()

    fun getAnimeByName(binding: FragmentMainBinding) {
        val rand = (4000..5000).random()
        disposable.add(
            mainRepository.getApiService().getAnime(rand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Anime", it.data.toString())
                    mainView.getAnimeByName(it, binding)
                }, {
                    Log.e("Error", it?.localizedMessage.toString())
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}