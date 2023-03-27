package com.madeean.day14.data.model

import com.google.gson.annotations.SerializedName

data class ModelMovieDetail(
    val budget: Int,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val status: String,
    val genres: List<ModelMovieGenre>
)
