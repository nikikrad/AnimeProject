package com.example.animeproject.presentation.anime_info

import com.example.animeproject.domain.response.AnimeResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FullAnimeInformationView: MvpView {

    @AddToEndSingle
    fun getAnimeById(animeResponse: AnimeResponse)
}