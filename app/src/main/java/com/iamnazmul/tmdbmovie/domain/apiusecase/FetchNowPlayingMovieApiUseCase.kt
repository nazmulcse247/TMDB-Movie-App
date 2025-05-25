package com.iamnazmul.tmdbmovie.domain.apiusecase

import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.domain.usecase.ApiUseCaseParams
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import javax.inject.Inject

class FetchNowPlayingMovieApiUseCase @Inject constructor(
    private val repository: MovieRepository
) : ApiUseCaseParams<FetchNowPlayingMovieApiUseCase.Params, List<NowPlayingMovieApiEntity>>{
    override suspend fun execute(params: Params) = repository.fetchNowPlayingMovie(params.page)

    data class Params(
        val page : Int
    )
}