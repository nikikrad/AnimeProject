package com.example.animeproject.presentation.anime_info

import android.content.res.Resources
import android.graphics.Color.green
import android.graphics.Color.red
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class FullAnimeInformationFragment : MvpAppCompatFragment(), FullAnimeInformationView {

    lateinit var binding: FragmentFullAnimeInformationBinding

    private val disposable = CompositeDisposable()
    private var ID: Int = 0
    private lateinit var description: String
    @Inject
    lateinit var fullAnimeInformationRepository: FullAnimeInformationRepository
    private val presenter: FullAnimeInformationPresenter by moxyPresenter { FullAnimeInformationPresenter(fullAnimeInformationRepository, this) }


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
        presenter.getAnimeById(ID)
//        disposable.add(
//            fullAnimeInformationPresenter.getAnimeById(ID)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    var animeById = it.data
//                    Log.e("ANIME", it.toString())
//                    try {
//                        Glide.with(view)
//                            .load(animeById[0].attributes.coverImage.original)
//                            .placeholder(R.drawable.ic_search)
//                            .into(binding.ivCover)
//                    } catch (e: Exception) {
//                        Log.e("Error", e.localizedMessage)
//                    }
//
//
//                    Glide.with(view)
//                        .load(animeById[0].attributes.posterImage.original)
//                        .placeholder(R.drawable.ic_search)
//                        .into(binding.ivPoster)
//
//
//
//                    binding.tvTitle.text = animeById[0].attributes.titles.en
//                    if (binding.tvTitle.text.isEmpty())
//                        binding.tvTitle.text = animeById[0].attributes.titles.en_jp
//                    binding.tvJapanTitle.text = animeById[0].attributes.titles.ja_jp
//                    if (animeById[0].attributes.averageRating != 0.0) {
//                        binding.tvRating.text =
//                            "Рейтинг: " + animeById[0].attributes.averageRating.toString()
//
//                        val resource: Resources = resources
//                        val textRedColor = resource.getColor(R.color.red, null)
//                        val textGreenColor = resource.getColor(R.color.green, null)
//
//                        if (animeById[0].attributes.averageRating >= 70.00) {
//                            binding.tvRating.setTextColor(textGreenColor)
//                        }
//                        if (animeById[0].attributes.averageRating <= 50.00) {
//                            binding.tvRating.setTextColor(textRedColor)
//                        }
//                    }
//
//                    binding.tvDescription.text = animeById[0].attributes.description
//                    description = animeById[0].attributes.description
//                    if (binding.tvDescription.layout.height >= 99) {
//                        binding.btnDescription.isVisible = true
//
//                    }
//                    if (animeById[0].attributes.episodeCount == 1) {
//                        binding.tvCountEpisodes.text = "1 эпизод - " + animeById[0].attributes.episodeLength + " минут"
//                    } else
//                        binding.tvCountEpisodes.text =
//                            "Аниме: " + animeById[0].attributes.episodeCount.toString() + " эпизодов по " + animeById[0].attributes.episodeLength + " минуты"
//                    binding.tvStartDate.text = animeById[0].attributes.startDate
//                    binding.tvEndDate.text = animeById[0].attributes.endDate
//                }, {
//                    Log.e("Error", it.localizedMessage)
//                })

//        )
        binding.btnDescription.setOnClickListener {
            val bundle = Bundle()
            val dialogFragment = DescriptionDialogFragment()
            bundle.putString("DESCRIPTION", description)
            dialogFragment.arguments = bundle
            dialogFragment.show(childFragmentManager, "h")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAnimeById(animeResponse: AnimeResponse) {
        ID = arguments?.getInt("ID")!!
        val animeById = animeResponse.data
        Log.e("ANIME", animeResponse.toString())
        try {
                Glide.with(binding.root)
                    .load(animeById[0].attributes.coverImage.original)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivCover)

        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
        }


        view?.let {
            Glide.with(it)
                .load(animeById[0].attributes.posterImage.original)
                .placeholder(R.drawable.ic_search)
                .into(binding.ivPoster)
        }

        binding.tvTitle.text = animeById[0].attributes.titles.en
        if (binding.tvTitle.text.isEmpty())
            binding.tvTitle.text = animeById[0].attributes.titles.en_jp
        binding.tvJapanTitle.text = animeById[0].attributes.titles.ja_jp
        if (animeById[0].attributes.averageRating != 0.0) {
            binding.tvRating.text =
                "Рейтинг: " + animeById[0].attributes.averageRating.toString()

            val resource: Resources = resources
            val textRedColor = resource.getColor(R.color.red, null)
            val textGreenColor = resource.getColor(R.color.green, null)

            if (animeById[0].attributes.averageRating >= 70.00) {
                binding.tvRating.setTextColor(textGreenColor)
            }
            if (animeById[0].attributes.averageRating <= 50.00) {
                binding.tvRating.setTextColor(textRedColor)
            }
        }

        binding.tvDescription.text = animeById[0].attributes.description
        description = animeById[0].attributes.description
        if (binding.tvDescription.layout.height >= 99) {
            binding.btnDescription.isVisible = true

        }
        if (animeById[0].attributes.episodeCount == 1) {
            binding.tvCountEpisodes.text = "1 эпизод - " + animeById[0].attributes.episodeLength + " минут"
        } else
            binding.tvCountEpisodes.text =
                "Аниме: " + animeById[0].attributes.episodeCount.toString() + " эпизодов по " + animeById[0].attributes.episodeLength + " минуты"
        binding.tvStartDate.text = animeById[0].attributes.startDate
        binding.tvEndDate.text = animeById[0].attributes.endDate
    }
}