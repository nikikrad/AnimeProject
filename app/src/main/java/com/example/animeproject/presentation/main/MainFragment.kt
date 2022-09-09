package com.example.animeproject.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.animeproject.databinding.FragmentMainBinding
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {

    lateinit var binding: FragmentMainBinding
    @Inject
    lateinit var mainRepository: MainRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainRepository.getAllAnime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.e("KEK", response.toString())
            }, {
                Log.e("KEK", it.localizedMessage!!)
            })

    }
}