package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class CoverImage (

	@SerializedName("tiny")
	val tiny : String,
	@SerializedName("original")
	val original : String = "null"
)