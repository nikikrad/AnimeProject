package com.example.animeproject.domain.response

import com.google.gson.annotations.SerializedName

data class DataResponse (

	@SerializedName("id")
	val id : Int,
	@SerializedName("attributes")
	val attributes : Attributes,
)