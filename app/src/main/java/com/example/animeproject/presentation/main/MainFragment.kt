package com.example.animeproject.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.anime_info.FullAnimeInformationView
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainFragment : MvpAppCompatFragment(), MainView {

    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var mainRepository: MainRepository

    private var disposable = CompositeDisposable()

    private val presenter: MainPresenter by moxyPresenter { MainPresenter(mainRepository, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.getAnimeByName()
//        disposable.add(mainPresenter.getAnimeByName()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("Anime", it.data.toString())
//
//                val adapter = MainAdapter(it.data)
//                binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
//                binding.rvAnime.adapter = adapter
//            }, {
//                Log.e("Error", it.localizedMessage.toString())
//            }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun getAnimeByName(anime: AnimeResponse) {
        val adapter = MainAdapter(anime.data)
        binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
        binding.rvAnime.adapter = adapter
    }

}