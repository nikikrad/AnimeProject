package com.example.animeproject.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var mainPresenter: MainPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    private var disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        var anime = mainPresenter.getAllAnime()
//        Log.e("Anime", )
//        mainRepository.getAllAnime()

//        Observable.create{ observable ->
//            mainRepository.getApiService().getAllAnime()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ list ->
//                    Log.d("KEK", list.toString())
//                    observable.onNext(list)
//                }, { throwable ->
//                    Log.e("BUG", throwable.toString())
//                })

//        disposable.add(mainPresenter.getAllAnime()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("Anime", it.data.toString())
//
//                val adapter = MainAdapter(it.data)
////                binding.rvAnime.layoutManager =
////                    LinearLayoutManager(
////                        activity?.applicationContext,
////                        LinearLayoutManager.VERTICAL,
////                        false
////                    )
//                binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
//                binding.rvAnime.adapter = adapter
//            }, {
//                Log.e("Error", it.localizedMessage.toString())
//            }))

        disposable.add(mainPresenter.getAnimeByName("fds")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Anime", it.data.toString())

                val adapter = MainAdapter(it.data)
                binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
                binding.rvAnime.adapter = adapter
            }, {
                Log.e("Error", it.localizedMessage.toString())
            }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}