package com.example.animeproject.presentation.anime_info.video

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.animeproject.databinding.ActivityVideoBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class VideoActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityVideoBinding
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    private var timeVideo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val videoId = intent.extras?.get("YTVideo").toString()


        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(videoId)
                p1?.setFullscreen(true)
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