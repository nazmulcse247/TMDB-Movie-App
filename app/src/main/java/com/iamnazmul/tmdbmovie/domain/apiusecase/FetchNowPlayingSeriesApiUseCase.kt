package com.iamnazmul.tmdbmovie.domain.apiusecase
import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.domain.usecase.ApiUseCaseNonParams
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import javax.inject.Inject

class FetchNowPlayingSeriesApiUseCase @Inject constructor(
    private val repository: MovieRepository
) : ApiUseCaseNonParams<List<NowPlayingSeriesApiEntity>>{
    override suspend fun execute() = repository.fetchNowPlayingSeries()
}