package com.example.animeproject.presentation.search

import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.domain.response.AnimeResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SearchView: MvpView {

    @AddToEndSingle
    fun getAnimeByName(animeResponse: AnimeResponse, binding: FragmentSearchBinding)
}