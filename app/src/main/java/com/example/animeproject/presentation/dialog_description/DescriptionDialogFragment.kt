package com.example.animeproject.presentation.dialog_description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.animeproject.R
import com.example.animeproject.databinding.DialogDescriptionBinding

class DescriptionDialogFragment : DialogFragment() {

    private lateinit var binding: DialogDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDescriptionBinding.inflate(inflater, container, false)

        binding.tvDescription.text = arguments?.getString("DESCRIPTION")
        return binding.root
    }
}