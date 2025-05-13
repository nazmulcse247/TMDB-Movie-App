package com.iamnazmul.tmdbmovie.domain.apiusecase
import com.iamnazmul.tmdbmovie.domain.repository.PopularMovieRepository
import com.iamnazmul.tmdbmovie.domain.usecase.ApiUseCaseNonParams
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import javax.inject.Inject

class PopularMovieApiUseCase @Inject constructor(
    private val repository: PopularMovieRepository
) : ApiUseCaseNonParams<List<PopularMovieApiEntity>>{
    override suspend fun execute() = repository.fetchPopularMovie()
}