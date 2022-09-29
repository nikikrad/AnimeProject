package com.example.animeproject.presentation.anime_info

import androidx.fragment.app.FragmentManager
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FullAnimeInformationView : MvpView {

    @AddToEndSingle
    fun getAnimeById(
        animeResponse: AnimeResponse,
        binding: FragmentFullAnimeInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    )
}