package com.example.animeproject.presentation.mult_info

import androidx.fragment.app.FragmentManager
import com.example.animeproject.databinding.FragmentFullMultInformationBinding
import com.example.animeproject.domain.response.MultResponse
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FullMultInformationView : MvpView {

    @AddToEndSingle
    fun getMultById(
        multResponse: MultResponse,
        binding: FragmentFullMultInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    )
}