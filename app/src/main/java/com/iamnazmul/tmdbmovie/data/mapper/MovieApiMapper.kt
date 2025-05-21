package com.iamnazmul.tmdbmovie.data.mapper

import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.iamnazmul.tmdbmovie.model.response.MovieApiResponse
import javax.inject.Inject

class MovieApiMapper @Inject constructor(

): Mapper<MovieApiResponse, List<MovieApiEntity>>{
    override fun mapFromApiResponse(type: MovieApiResponse): List<MovieApiEntity> {
        return type.results?.map { popularMovie ->
            MovieApiEntity(
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