package com.example.animeproject.presentation.anime_info.model_request

data class Comments(
    val comment_id: String,
    val movie_id : String,
    val user_name: String,
    val comment: String
)
