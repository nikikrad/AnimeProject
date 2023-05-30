package com.example.animeproject.presentation.search

import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.domain.response.MultResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SearchView: MvpView {

    @AddToEndSingle
    fun getMultByName(multResponse: MultResponse, binding: FragmentSearchBinding)

    @AddToEndSingle
    fun getMultByGenre(multResponse: MultResponse, binding: FragmentSearchBinding)
}