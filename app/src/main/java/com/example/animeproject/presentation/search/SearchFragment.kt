package com.example.animeproject.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.anime_info.FullAnimeInformationPresenter
import com.example.animeproject.presentation.search.repository.SearchRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.MvpFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : MvpAppCompatFragment(), SearchView {
    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchRepository: SearchRepository

    private val presenter: SearchPresenter by moxyPresenter {
        SearchPresenter(
            searchRepository,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSend.setOnClickListener {
            presenter.getAnimeByName(binding.etNameAnime.text.toString(), binding)
        }
    }

    override fun getAnimeByName(animeResponse: AnimeResponse, bind: FragmentSearchBinding) {
        binding = bind
        val adapter = SearchAdapter(animeResponse.data)
        binding.rvAnime.layoutManager = LinearLayoutManager(
            activity?.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvAnime.adapter = adapter
    }

}