package com.example.animeproject.presentation.anime_info.model_request

data class AnimeRequest(
    var id: String? = "null",
    var description: String? = "null",
    var title: String? = "null",
    var rating: String? = "null",
    var startDate: String? = "null",
    var endDate: String? = "null",
    var poster: String? = "null",
    var episodeCount: String? = "null",
    var episodeLength: String? = "null",
    var status: Boolean
)
