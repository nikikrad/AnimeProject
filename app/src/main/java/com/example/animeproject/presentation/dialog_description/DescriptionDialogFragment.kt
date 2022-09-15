package com.example.animeproject.presentation.dialog_description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.animeproject.R
import com.example.animeproject.databinding.DialogDescriptionBinding

class DescriptionDialogFragment: DialogFragment() {

    private lateinit var binding: DialogDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_description, container, false)
        binding = DialogDescriptionBinding.inflate(inflater, container, false)

        var description = arguments?.getString("DESCRIPTION")
        binding.tvDescription.text = description
        return rootView
    }

    fun dismissDialog() {
        try{
            dismiss()
        }catch (e: Exception){
            Log.e("Error: ", e.toString())
        }
    }
}