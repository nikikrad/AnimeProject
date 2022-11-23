package com.example.animeproject.presentation.anime_info.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animeproject.databinding.FragmentDialogCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SendCommentSheetDialog: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDialogCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog!!.window!!.setBackgroundDrawableResource(Color.TRANSPARENT)

    }
}