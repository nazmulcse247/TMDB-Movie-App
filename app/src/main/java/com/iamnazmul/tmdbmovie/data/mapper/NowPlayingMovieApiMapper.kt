package com.iamnazmul.tmdbmovie.data.mapper
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.response.NowPlayingMovieApiResponse
import javax.inject.Inject

class NowPlayingMovieApiMapper @Inject constructor(

): Mapper<NowPlayingMovieApiResponse, List<NowPlayingMovieApiEntity>>{
    override fun mapFromApiResponse(type: NowPlayingMovieApiResponse): List<NowPlayingMovieApiEntity> {
        return type.results?.map { popularMovie ->
            NowPlayingMovieApiEntity(
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