package com.example.animeproject.presentation.main

import android.util.Log
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.presentation.main.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class MainPresenter(
    private val mainRepository: MainRepository,
    private val mainView: MainView
) : MvpPresenter<MainView>() {

    private var disposable = CompositeDisposable()

    fun getMult(binding: FragmentMainBinding) {
        val rand = (5000..6000).random()
        disposable.add(
            mainRepository.getApiService().getMult(rand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Anime", it.data.toString())
                    mainView.getMult(it, binding)
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