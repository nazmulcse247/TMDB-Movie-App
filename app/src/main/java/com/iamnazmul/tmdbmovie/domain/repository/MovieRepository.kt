package com.iamnazmul.tmdbmovie.domain.repository

import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMovie() : Flow<ApiResult<List<MovieApiEntity>>>
    suspend fun fetchNowPlayingMovie(page : Int) : Flow<ApiResult<List<MovieApiEntity>>>
    suspend fun fetchNowPlayingSeries() : Flow<ApiResult<List<MovieApiEntity>>>

}