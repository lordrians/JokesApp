package com.example.jokesapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JokesResponse(

	@field:SerializedName("JokesResponse")
	val jokesResponse: List<JokesResponseItem?>? = null
) : Parcelable

@Parcelize
data class JokesResponseItem(

	@field:SerializedName("punchline")
	val punchline: String? = null,

	@field:SerializedName("setup")
	val setup: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
