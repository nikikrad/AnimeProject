package com.example.animeproject.presentation.mult_info.video

import android.os.Bundle
import android.widget.Toast
import com.example.animeproject.BuildConfig
import com.example.animeproject.databinding.ActivityVideoBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class VideoActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityVideoBinding
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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
            BuildConfig.API_KEY,
            youtubePlayerInit
        )
    }
}