package com.example.animeproject.presentation.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.animeproject.databinding.DialogDescriptionBinding
import com.example.animeproject.databinding.DialogFilterBinding
import com.example.animeproject.presentation.search.filter.Filter

class FilterDialogFragment(
    private val getAnimeByGenre: (Int) -> Unit,
): DialogFragment() {

    private lateinit var binding: DialogFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            when(Filter.getActiveGenre()){
                0->{
                    rbAll.isChecked = true
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                1->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = true
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                2->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = true
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                3->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = true
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                4->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = true
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                5->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = true
                    rbHorror.isChecked = false
                    rbMusic.isChecked = false
                }
                6->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = true
                    rbMusic.isChecked = false
                }
                7->{
                    rbAll.isChecked = false
                    rbComedy.isChecked = false
                    rbAdventure.isChecked = false
                    rbIsekai.isChecked = false
                    rbSchool.isChecked = false
                    rbMagic.isChecked = false
                    rbHorror.isChecked = false
                    rbMusic.isChecked = true
                }
            }
        }

        binding.apply {
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                when(radioGroup.checkedRadioButtonId){
                    rbAll.id -> {
                        rbAll.isChecked = true
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(0)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbComedy.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = true
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(1)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbAdventure.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = true
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(2)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbIsekai.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = true
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(3)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbSchool.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = true
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(4)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbMagic.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = true
                        rbHorror.isChecked = false
                        rbMusic.isChecked = false
                        Filter.changeGenre(5)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbHorror.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = true
                        rbMusic.isChecked = false
                        Filter.changeGenre(6)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                    rbMusic.id -> {
                        rbAll.isChecked = false
                        rbComedy.isChecked = false
                        rbAdventure.isChecked = false
                        rbIsekai.isChecked = false
                        rbSchool.isChecked = false
                        rbMagic.isChecked = false
                        rbHorror.isChecked = false
                        rbMusic.isChecked = true
                        Filter.changeGenre(7)
                        getAnimeByGenre
                        dialog?.dismiss()
                    }
                }
            }
        }
    }
}