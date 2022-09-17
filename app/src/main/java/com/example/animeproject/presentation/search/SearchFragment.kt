package com.example.animeproject.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.presentation.main.MainAdapter
import com.google.android.material.elevation.ElevationOverlayProvider
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment: Fragment() {
    lateinit var binding: FragmentSearchBinding
    @Inject
    lateinit var searchPresenter: SearchPresenter

    private var disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSend.setOnClickListener{
            disposable.add(searchPresenter.getAnimeByName(binding.etNameAnime.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Anime", it.data.toString())

                    val adapter = SearchAdapter(it.data)
                    binding.rvAnime.layoutManager = LinearLayoutManager(
                        activity?.applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    binding.rvAnime.adapter = adapter
                }, {
                    Log.e("Error", it.localizedMessage.toString())
                }))

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}