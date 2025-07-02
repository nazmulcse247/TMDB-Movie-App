package com.iamnazmul.tmdbmovie.model.entity

data class DiscoverMovieApiEntity(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    var isBookmarked: Boolean = false,
)