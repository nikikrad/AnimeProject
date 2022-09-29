package com.example.animeproject.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

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
        presenter.getAnimeByName(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun getAnimeByName(anime: AnimeResponse, bind: FragmentMainBinding) {
        binding = bind
        val adapter = MainAdapter(anime.data)
        binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
        binding.rvAnime.adapter = adapter
    }

}