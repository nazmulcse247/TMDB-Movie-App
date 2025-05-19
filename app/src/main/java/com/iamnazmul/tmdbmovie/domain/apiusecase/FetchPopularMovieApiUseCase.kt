package com.iamnazmul.tmdbmovie.domain.apiusecase
import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.domain.usecase.ApiUseCaseNonParams
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import javax.inject.Inject

class FetchPopularMovieApiUseCase @Inject constructor(
    private val repository: MovieRepository
) : ApiUseCaseNonParams<List<PopularMovieApiEntity>>{
    override suspend fun execute() = repository.fetchPopularMovie()
}