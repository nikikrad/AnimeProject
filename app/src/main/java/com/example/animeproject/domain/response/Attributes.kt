package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class Attributes (

    @SerializedName("description")//1111111111111
    val description : String,
    @SerializedName("titles")//1111111111111
    val titles : Titles,
    @SerializedName("averageRating")//1111111111111
    val averageRating : Double,
    @SerializedName("startDate")//1111111111111
    val startDate : String,
    @SerializedName("endDate")//1111111111111
    val endDate : String,
    @SerializedName("nextRelease")
    val nextRelease : String,
    @SerializedName("status")
    val status : String,
    @SerializedName("posterImage")//1111111111111
    val posterImage : PosterImage,
    @SerializedName("coverImage")//1111111111111
    val coverImage : CoverImage,
    @SerializedName("episodeCount")
    val episodeCount : Int,
    @SerializedName("episodeLength")
    val episodeLength : Int,
)