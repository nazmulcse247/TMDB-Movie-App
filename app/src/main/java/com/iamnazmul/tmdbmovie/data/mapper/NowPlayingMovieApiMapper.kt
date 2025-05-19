package com.iamnazmul.tmdbmovie.data.mapper
import com.iamnazmul.tmdbmovie.model.entity.Dates
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovie
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.response.NowPlayingMovieApiResponse
import javax.inject.Inject

class NowPlayingMovieApiMapper @Inject constructor(

) : Mapper<NowPlayingMovieApiResponse, NowPlayingMovieApiEntity>{
    override fun mapFromApiResponse(type: NowPlayingMovieApiResponse): NowPlayingMovieApiEntity {
        return NowPlayingMovieApiEntity(
            dates = Dates(
                maximum = type.dates?.maximum ?: "",
                minimum = type.dates?.minimum ?: ""
            ),
            nowPlayingMovie = type.results?.map { nowPlayingMovie ->
                NowPlayingMovie(
                    adult = nowPlayingMovie?.adult == false,
                    backdropPath = nowPlayingMovie?.backdrop_path ?: "",
                    id = nowPlayingMovie?.id ?: 0,
                    originalTitle = nowPlayingMovie?.original_title ?: "",
                    popularity = nowPlayingMovie?.popularity ?: 0.0,
                    posterPath = nowPlayingMovie?.poster_path ?: "",
                    video = nowPlayingMovie?.video == false,
                    voteCount = nowPlayingMovie?.vote_count ?: 0,
                    voteAverage = nowPlayingMovie?.vote_average ?: 0.0
                )

            } ?: emptyList()
        )
    }
}