package com.iamnazmul.tmdbmovie.domain.usecase

import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseParams<Params, Type> : BaseUseCase {
    suspend fun execute(params: Params): Flow<ApiResult<Type>>
}

interface ApiUseCaseNonParams<Type> : BaseUseCase {
    suspend fun execute(): Flow<ApiResult<Type>>
}