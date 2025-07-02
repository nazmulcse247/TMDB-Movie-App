package com.iamnazmul.tmdbmovie.domain.usecase

import androidx.paging.PagingData
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.base.BaseUseCase
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseParams<Params, Type> : BaseUseCase {
    suspend fun execute(params: Params): Flow<ApiResult<Type>>
}

interface ApiUseCaseNonParams<Type> : BaseUseCase {
    suspend fun execute(): Flow<ApiResult<Type>>
}