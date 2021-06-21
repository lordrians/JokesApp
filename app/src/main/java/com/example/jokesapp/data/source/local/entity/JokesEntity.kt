package com.example.jokesapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_jokes")
data class JokesEntity(

	@ColumnInfo(name = "punchline")
	val punchline: String? = null,

	@ColumnInfo(name = "setup")
	val setup: String? = null,

	@PrimaryKey
	@ColumnInfo(name = "id")
	val id: Int? = null,

	@ColumnInfo(name = "type")
	val type: String? = null
)
