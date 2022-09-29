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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.domain.response.Attributes
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.presentation.anime_info.model_request.AnimeRequest
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import com.example.animeproject.presentation.setting.response.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class FullAnimeInformationFragment : MvpAppCompatFragment(), FullAnimeInformationView {

    lateinit var binding: FragmentFullAnimeInformationBinding

    private val disposable = CompositeDisposable()
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
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullAnimeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        dialogFragment = DescriptionDialogFragment()
        ID = arguments?.getInt("ID")!!
        presenter.getAnimeById(ID, binding, dialogFragment, childFragmentManager)

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAnimeById(
        animeResponse: AnimeResponse,
        bind: FragmentFullAnimeInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    ) {
        binding = bind
        ID = arguments?.getInt("ID")!!
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
            binding.tvCountEpisodes.text =
                "1 эпизод - " + animeById[0].attributes.episodeLength + " минут"
        } else
            binding.tvCountEpisodes.text =
                "Аниме: " + animeById[0].attributes.episodeCount.toString() + " эпизодов по " + animeById[0].attributes.episodeLength + " минуты"
        binding.tvStartDate.text = animeById[0].attributes.startDate
        binding.tvEndDate.text = animeById[0].attributes.endDate

        binding.btnFavorite.setOnClickListener {
            if (auth.currentUser != null) {
                if (checkerDBForUser(animeById[0].id.toString())) {
                    database.child(auth.currentUser?.email.toString().substringBefore("@"))
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
                                animeById[0].attributes.episodeLength.toString()
                            )
                        )
                } else {
                    database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
                        .addOnSuccessListener {
                            it.child(animeById[0].id.toString()).ref.removeValue()
                        }
                }
            } else {
                Toast.makeText(context, "Войдите в аккаунт!", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnDescription.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("DESCRIPTION", animeById[0].attributes.description)
            dialog.arguments = bundle
            dialog.show(fragmentManager, "h")
        }
    }

    private fun checkerDBForUser(id: String): Boolean {
        var megaStatus = true
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener {
                it.children.forEach { data ->
                    if (data.child("id").value.toString() == id) {
                        megaStatus = false
                    }
                }
            }
        return megaStatus
    }
}