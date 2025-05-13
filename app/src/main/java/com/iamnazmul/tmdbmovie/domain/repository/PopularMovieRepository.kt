package com.iamnazmul.tmdbmovie.domain.repository

import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {

    suspend fun fetchPopularMovie() : Flow<ApiResult<List<PopularMovieApiEntity>>>
}