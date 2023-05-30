package com.example.animeproject.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.MultResponse
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : MvpAppCompatFragment(), MainView {

    private lateinit var binding: FragmentMainBinding
    @Inject
    lateinit var mainRepository: MainRepository
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
        presenter.getMult(binding)
        binding.swipeToRefresh.setOnRefreshListener {
            refreshMainView()
        }
    }

    override fun getMult(anime: MultResponse, bind: FragmentMainBinding) {
        binding = bind
        binding.pbLoading.isVisible = anime.data.isEmpty()
        val adapter = MainAdapter(anime.data)
        binding.rvAnime.layoutManager = GridLayoutManager(context, 2)
        binding.rvAnime.adapter = adapter
    }

    private fun refreshMainView(){
        presenter.getMult(binding)
        binding.swipeToRefresh.isRefreshing = false
    }
}