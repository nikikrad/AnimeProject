package com.example.animeproject.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentFavoriteBinding
import com.example.animeproject.presentation.mult_info.model_request.AnimeRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    @Inject
    lateinit var database: DatabaseReference
    @Inject
    lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (auth.currentUser == null) {
            binding.rvWatched.isVisible = false
            binding.tvAuth.isVisible = true
            binding.pbLoading.isVisible = false
            binding.pbLoading.isVisible = false
        } else {
            binding.rvWatched.isVisible = true
            binding.tvAuth.isVisible = false
            binding.pbLoading.isVisible = true
            processDataFromDatabase()
        }

        binding.firstSwipeToRefresh.setOnRefreshListener {
            refreshMainView()
        }
    }

    private var watchedMultList: MutableList<AnimeRequest> = emptyList<AnimeRequest>().toMutableList()
    private var unwatchedMultList: MutableList<AnimeRequest> = emptyList<AnimeRequest>().toMutableList()
    private lateinit var adapterWatched: FavoriteWatchedAdapter
    private lateinit var adapterUnWatched : FavoriteUnwatchedAdapter

    private fun processDataFromDatabase() {
        watchedMultList.clear()
        unwatchedMultList.clear()
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener { animeId ->
                animeId.children.forEach { aboutAnime ->
                    if (aboutAnime.child("status").value as Boolean){
                        watchedMultList.add(
                            AnimeRequest(
                                aboutAnime.child("id").value.toString(),
                                aboutAnime.child("description").value.toString(),
                                aboutAnime.child("title").value.toString(),
                                aboutAnime.child("rating").value.toString(),
                                aboutAnime.child("startDate").value.toString(),
                                aboutAnime.child("endDate").value.toString(),
                                aboutAnime.child("poster").value.toString(),
                                aboutAnime.child("episodeCount").value.toString(),
                                aboutAnime.child("episodeLength").value.toString(),
                                aboutAnime.child("status").value as Boolean
                            )
                        )
                    }else{
                        unwatchedMultList.add(
                            AnimeRequest(
                                aboutAnime.child("id").value.toString(),
                                aboutAnime.child("description").value.toString(),
                                aboutAnime.child("title").value.toString(),
                                aboutAnime.child("rating").value.toString(),
                                aboutAnime.child("startDate").value.toString(),
                                aboutAnime.child("endDate").value.toString(),
                                aboutAnime.child("poster").value.toString(),
                                aboutAnime.child("episodeCount").value.toString(),
                                aboutAnime.child("episodeLength").value.toString(),
                                aboutAnime.child("status").value as Boolean
                            )
                        )
                    }
                }
                binding.pbLoading.isVisible = watchedMultList.isEmpty()
                adapterWatched = FavoriteWatchedAdapter(watchedMultList)
                binding.tvWatchedAnimeLabel.text = "Просмотренныу мультфильмы - ${watchedMultList.size}"
                adapterWatched.notifyDataSetChanged()
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
                linearLayoutManager.reverseLayout = true
                linearLayoutManager.stackFromEnd = true
                binding.rvWatched.layoutManager = linearLayoutManager
                binding.rvWatched.adapter = adapterWatched

                adapterUnWatched = FavoriteUnwatchedAdapter(unwatchedMultList)
                binding.tvUnWatchedAnimeLabel.text = "Не просмотренныу мультфильмы - ${unwatchedMultList.size}"
                adapterUnWatched.notifyDataSetChanged()
                val unWatchedLinearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
                unWatchedLinearLayoutManager.reverseLayout = true
                unWatchedLinearLayoutManager.stackFromEnd = true
                binding.rvUnWatched.layoutManager = unWatchedLinearLayoutManager
                binding.rvUnWatched.adapter = adapterUnWatched
            }
    }

    fun refreshMainView(){
        binding.rvWatched.recycledViewPool.clear()
        binding.rvUnWatched.recycledViewPool.clear()
        adapterWatched.notifyDataSetChanged()
        adapterUnWatched.notifyDataSetChanged()

        processDataFromDatabase()
        binding.firstSwipeToRefresh.isRefreshing = false

    }
}