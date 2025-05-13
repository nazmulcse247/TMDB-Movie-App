package com.iamnazmul.tmdbmovie.domain.usecase

import com.iamnazmul.tmdbmovie.domain.base.BaseUseCase


interface CoroutineBaseUseCase<Params, Type>: BaseUseCase {
    suspend fun execute(params: Params):Type
}

interface RoomUseCaseNonParams<Type> : BaseUseCase {
    suspend fun execute(): Type
}