package com.example.animeproject.presentation.anime_info.video

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.animeproject.R
import com.example.animeproject.databinding.ActivityMainBinding
import com.example.animeproject.databinding.FragmentVideoBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragment

class VideoFragment : YouTubeBaseActivity() {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val videoId = intent.extras?.get("YTVideo").toString()


        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }


        }
        binding.youtubePlayer.initialize(
            "AIzaSyCF1j71oYhcrKqet9yAOlRS2LA2iz05k3E",
            youtubePlayerInit
        )
    }
}