package com.example.animeproject.domain.response

data class Attributes(
    val description: String,
    val titles: Titles,
    val averageRating: String,
    val startDate: String,
    val endDate: String,
    val ageRatingGuide: String,
    val posterImage: PosterImage,
    val coverImage: CoverImage,
    val episodeCount: Int
)
