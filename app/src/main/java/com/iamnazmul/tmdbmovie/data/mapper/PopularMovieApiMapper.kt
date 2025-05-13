package com.iamnazmul.tmdbmovie.data.mapper

import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import com.iamnazmul.tmdbmovie.model.response.PopularMovieApiResponse
import javax.inject.Inject

class PopularMovieApiMapper @Inject constructor(

): Mapper<PopularMovieApiResponse, List<PopularMovieApiEntity>>{
    override fun mapFromApiResponse(type: PopularMovieApiResponse): List<PopularMovieApiEntity> {
        return type.results?.map { popularMovie ->
            PopularMovieApiEntity(
                adult = popularMovie?.adult == false,
                backdropPath = popularMovie?.backdrop_path ?: "",
                id = popularMovie?.id ?: 0,
                originalTitle = popularMovie?.original_title ?: "",
                popularity = popularMovie?.popularity ?: 0.0,
                posterPath = popularMovie?.poster_path ?: "",
                video = popularMovie?.video == false,
                voteCount = popularMovie?.vote_count ?: 0,
                voteAverage = popularMovie?.vote_average ?: 0.0
            )
        } ?: emptyList()
    }

}