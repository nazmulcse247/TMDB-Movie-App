package com.iamnazmul.tmdbmovie.domain.repository

import androidx.paging.PagingData
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMovie() : Flow<ApiResult<List<MovieApiEntity>>>
    suspend fun fetchNowPlayingMovie(page : Int) : Flow<ApiResult<List<NowPlayingMovieApiEntity>>>
    suspend fun fetchNowPlayingSeries() : Flow<ApiResult<List<NowPlayingSeriesApiEntity>>>
    fun fetchDiscoverMoviesPaged(sortBy : String): Flow<PagingData<DiscoverMovieApiEntity>>

}