package com.example.animeproject.presentation.main

import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.domain.response.MultResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun getMult(anime: MultResponse, binding: FragmentMainBinding)

}