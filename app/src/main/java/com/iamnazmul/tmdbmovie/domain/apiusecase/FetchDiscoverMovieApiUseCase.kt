package com.iamnazmul.tmdbmovie.domain.apiusecase

import androidx.paging.PagingData
import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDiscoverMoviePagedUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(sortBy: String): Flow<PagingData<DiscoverMovieApiEntity>> {
        return repository.fetchDiscoverMoviesPaged(sortBy)
    }
}