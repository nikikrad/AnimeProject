package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class Titles (

	@SerializedName("en") val en : String,
	@SerializedName("ja_jp") val ja_jp : String
)