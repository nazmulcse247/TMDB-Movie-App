package com.iamnazmul.tmdbmovie.model.entity

data class PopularMovieApiEntity(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
