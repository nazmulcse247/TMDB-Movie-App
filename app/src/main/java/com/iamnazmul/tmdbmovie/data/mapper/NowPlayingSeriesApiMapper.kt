package com.iamnazmul.tmdbmovie.data.mapper

import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import com.iamnazmul.tmdbmovie.model.response.NowPlayingSeriesApiResponse
import javax.inject.Inject

class NowPlayingSeriesApiMapper @Inject constructor(

): Mapper<NowPlayingSeriesApiResponse, List<NowPlayingSeriesApiEntity>>{
    override fun mapFromApiResponse(type: NowPlayingSeriesApiResponse): List<NowPlayingSeriesApiEntity> {
        return type.results?.map { popularMovie ->
            NowPlayingSeriesApiEntity(
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