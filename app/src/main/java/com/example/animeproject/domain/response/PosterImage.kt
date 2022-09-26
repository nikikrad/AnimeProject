package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class PosterImage (

	@SerializedName("original")
	val original : String = "null"
)