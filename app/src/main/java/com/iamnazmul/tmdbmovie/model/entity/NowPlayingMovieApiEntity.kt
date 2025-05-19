package com.iamnazmul.tmdbmovie.model.entity

class NowPlayingMovieApiEntity(
    val dates: Dates,
    val nowPlayingMovie: List<NowPlayingMovie>,
)

data class Dates(
    val maximum: String?,
    val minimum: String?
)

data class NowPlayingMovie(
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