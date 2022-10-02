package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class Attributes (

    @SerializedName("description")
    val description : String,
    @SerializedName("titles")
    val titles : Titles,
    @SerializedName("averageRating")
    val averageRating : Double,
    @SerializedName("startDate")
    val startDate : String,
    @SerializedName("endDate")
    val endDate : String,
    @SerializedName("nextRelease")
    val nextRelease : String,
    @SerializedName("ageRatingGuide")
    val ageRating: String,
    @SerializedName("status")
    val status : String,
    @SerializedName("posterImage")
    val posterImage : PosterImage,
    @SerializedName("coverImage")
    val coverImage : CoverImage,
    @SerializedName("episodeCount")
    val episodeCount : Int,
    @SerializedName("episodeLength")
    val episodeLength : Int,
    @SerializedName("youtubeVideoId")
    val youtubeVideo: String
)