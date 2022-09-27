package com.example.animeproject.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentFavoriteBinding
import com.example.animeproject.presentation.anime_info.model_request.AnimeRequest
import com.example.animeproject.presentation.main.MainAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        if (auth.currentUser == null) {
            binding.rvFavorite.isVisible = false
            binding.tvAuth.isVisible = true
        } else {
            binding.rvFavorite.isVisible = true
            binding.tvAuth.isVisible = false
            processDataFromDatabase()
        }
    }

    var animeList: MutableList<AnimeRequest> = emptyList<AnimeRequest>().toMutableList()

    private fun processDataFromDatabase() {
        animeList.clear()
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener { animeId ->
                animeId.children.forEach { aboutAnime ->
                    animeList.add(
                        AnimeRequest(
                            aboutAnime.child("id").value.toString(),
                            aboutAnime.child("description").value.toString(),
                            aboutAnime.child("title").value.toString(),
                            aboutAnime.child("rating").value.toString(),
                            aboutAnime.child("startDate").value.toString(),
                            aboutAnime.child("endDate").value.toString(),
                            aboutAnime.child("poster").value.toString(),
                            aboutAnime.child("episodeCount").value.toString(),
                            aboutAnime.child("episodeLength").value.toString()
                        )
                    )
                }
                val adapter = FavoriteAdapter(animeList)
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
                linearLayoutManager.reverseLayout = true
                linearLayoutManager.stackFromEnd = true
                binding.rvFavorite.layoutManager = linearLayoutManager
                binding.rvFavorite.adapter = adapter
            }
    }
}