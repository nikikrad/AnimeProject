package com.example.animeproject.presentation.anime_info

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.presentation.anime_info.model_request.AnimeRequest
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.anime_info.video.VideoActivity
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
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

    private var disposable = CompositeDisposable()
    private var ID: Int = 0
    private lateinit var description: String
    private lateinit var animeById: List<DataResponse>
    private lateinit var dialogFragment: DescriptionDialogFragment

    @Inject
    lateinit var fullAnimeInformationRepository: FullAnimeInformationRepository
    private val presenter: FullAnimeInformationPresenter by moxyPresenter {
        FullAnimeInformationPresenter(
            fullAnimeInformationRepository,
            this
        )
    }
    @Inject
    lateinit var database: DatabaseReference
    @Inject
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullAnimeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dialogFragment = DescriptionDialogFragment()
        ID = arguments?.getInt("ID")!!
        presenter.getAnimeById(ID, binding, dialogFragment, childFragmentManager)

        binding.btnBack.setOnClickListener {
            binding.btnBack.animation = AnimationUtils.loadAnimation(context, R.anim.scale_anim)
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAnimeById(
        animeResponse: AnimeResponse,
        bind: FragmentFullAnimeInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    ) {
        binding = bind
        binding.pbLoading.isVisible = animeResponse.data.isEmpty()
        animeById = animeResponse.data

        Log.e("ANIME", animeResponse.toString())
        try {
            Glide.with(binding.root)
                .load(animeById[0].attributes.coverImage.original)
                .placeholder(R.drawable.ic_search)
                .into(binding.ivCover)

        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
        }

        try {
            view?.let {
                Glide.with(it)
                    .load(animeById[0].attributes.posterImage.original)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivPoster)
            }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
        }


        binding.tvTitle.text = animeById[0].attributes.titles.en
        if (binding.tvTitle.text.isEmpty())
            binding.tvTitle.text = animeById[0].attributes.titles.en_jp
        binding.tvJapanTitle.text = animeById[0].attributes.titles.ja_jp
        if (animeById[0].attributes.averageRating != 0.0) {
            binding.tvRating.text =
                "??????????????: " + animeById[0].attributes.averageRating.toString()

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
            binding.tvCountEpisodes.text =
                "1 ???????????? - " + animeById[0].attributes.episodeLength + " ??????????"
        } else
            binding.tvCountEpisodes.text =
                "??????????: " + animeById[0].attributes.episodeCount.toString() + " ???????????????? ???? " + animeById[0].attributes.episodeLength + " ????????????"
        binding.tvStartDate.text = animeById[0].attributes.startDate
        binding.tvEndDate.text = animeById[0].attributes.endDate

        disposable.add(
            presenter.getStatusAnime(animeById[0].id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ statusAnime ->
                    if (statusAnime) {
                        binding.btnFavorite.isVisible = true
                        binding.btnFavorite.setImageResource(R.drawable.ic_baseline_empty_star)
                    } else{
                        binding.btnFavorite.isVisible = true
                        binding.btnFavorite.setImageResource(R.drawable.ic_baseline_full_star)
                    }

                    binding.btnFavorite.setOnClickListener {
                        if (auth.currentUser != null) {
                            if (statusAnime) {
                                database.child(
                                    auth.currentUser?.email.toString().substringBefore("@")
                                )
                                    .child(animeById[0].id.toString()).setValue(
                                        AnimeRequest(
                                            animeById[0].id.toString(),
                                            animeById[0].attributes.description,
                                            animeById[0].attributes.titles.en_jp,
                                            animeById[0].attributes.averageRating.toString(),
                                            animeById[0].attributes.startDate,
                                            animeById[0].attributes.endDate,
                                            animeById[0].attributes.posterImage.original,
                                            animeById[0].attributes.episodeCount.toString(),
                                            animeById[0].attributes.episodeLength.toString(),
                                            status = false
                                        )
                                    )
                                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_full_star)
                            } else {
                                database.child(
                                    auth.currentUser?.email.toString().substringBefore("@")
                                ).get()
                                    .addOnSuccessListener {
                                        it.child(animeById[0].id.toString()).ref.removeValue()
                                    }
                                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_empty_star)
                            }
                        } else {
                            Toast.makeText(context, "?????????????? ?? ??????????????!", Toast.LENGTH_SHORT).show()
                        }

                    }
                }, {
                    Log.e("Error", it.localizedMessage)
                })
        )
        binding.btnDescription.setOnClickListener {
            binding.btnDescription.animation = AnimationUtils.loadAnimation(context, R.anim.alpha_anim)
            val bundle = Bundle()
            bundle.putString("DESCRIPTION", animeById[0].attributes.description)
            dialog.arguments = bundle
            dialog.show(fragmentManager, "h")
        }

        binding.btnVideo.setOnClickListener {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("YTVideo", animeById[0].attributes.youtubeVideo)
            startActivity(intent)
        }

    }
}