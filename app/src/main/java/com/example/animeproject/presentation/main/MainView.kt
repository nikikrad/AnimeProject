package com.example.animeproject.presentation.main

import android.content.Context
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.AnimeResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun getAnimeByName(anime: AnimeResponse, binding: FragmentMainBinding)

}