package com.example.animeproject.presentation.anime_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class FullAnimeInformationFragment: Fragment() {

    lateinit var binding: FragmentFullAnimeInformationBinding
    @Inject
    lateinit var fullAnimeInformationPresenter: FullAnimeInformationPresenter
    private val disposable = CompositeDisposable()
    private var ID: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullAnimeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ID = arguments?.getInt("ID")!!
        disposable.add(fullAnimeInformationPresenter.getAnimeById(ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.e("ANIME", it.toString())
                Glide.with(view)
                    .load(it.data.attributes.coverImage.original)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivCover)

                Glide.with(view)
                    .load(it.data.attributes.posterImage)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivPoster)

                binding.tvTitle.text = it.data.attributes.titles.en_jp
            },{

            })
        )
    }
}