package com.iamnazmul.tmdbmovie.domain.repository

import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMovie() : Flow<ApiResult<List<PopularMovieApiEntity>>>
    suspend fun fetchNowPlayingMovie(page : Int) : Flow<ApiResult<NowPlayingMovieApiEntity>>

}