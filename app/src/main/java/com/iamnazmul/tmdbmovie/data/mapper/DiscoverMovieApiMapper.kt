package com.iamnazmul.tmdbmovie.data.mapper

import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import com.iamnazmul.tmdbmovie.model.response.DiscoverMovieApiResponse
import javax.inject.Inject

class DiscoverMovieApiMapper @Inject constructor(

) : Mapper<DiscoverMovieApiResponse, List<DiscoverMovieApiEntity>>{
    override fun mapFromApiResponse(type: DiscoverMovieApiResponse): List<DiscoverMovieApiEntity> {
        return type.results?.map { discoverMovie ->
            DiscoverMovieApiEntity(
                adult = discoverMovie?.adult == false,
                backdropPath = discoverMovie?.backdrop_path ?: "",
                id = discoverMovie?.id ?: 0,
                originalTitle = discoverMovie?.original_title ?: "",
                popularity = discoverMovie?.popularity ?: 0.0,
                posterPath = discoverMovie?.poster_path ?: "",
                video = discoverMovie?.video == false,
                voteCount = discoverMovie?.vote_count ?: 0,
                voteAverage = discoverMovie?.vote_average ?: 0.0
            )
        } ?: emptyList()
    }
}