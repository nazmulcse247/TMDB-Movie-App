package com.iamnazmul.tmdbmovie.data.repoimpl.remote

import com.iamnazmul.tmdbmovie.data.NetworkBoundResource
import com.iamnazmul.tmdbmovie.data.apiservice.MovieApiService
import com.iamnazmul.tmdbmovie.data.mapper.MovieApiMapper
import com.iamnazmul.tmdbmovie.data.mapper.NowPlayingMovieApiMapper
import com.iamnazmul.tmdbmovie.data.mapper.NowPlayingSeriesApiMapper
import com.iamnazmul.tmdbmovie.data.mapper.mapFromApiResponse
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val movieApiMapper: MovieApiMapper,
    private val nowPlayingMovieApiMapper: NowPlayingMovieApiMapper,
    private val nowPlayingSeriesApiMapper: NowPlayingSeriesApiMapper,
    private val networkBoundResources: NetworkBoundResource
) : MovieRepository{
    override suspend fun fetchPopularMovie(): Flow<ApiResult<List<MovieApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchPopularMovie()
            }, mapper = movieApiMapper
        )
    }

    override suspend fun fetchNowPlayingMovie(page: Int): Flow<ApiResult<List<NowPlayingMovieApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchNowPlayingMovie(page)
            } , mapper = nowPlayingMovieApiMapper
        )
    }

    override suspend fun fetchNowPlayingSeries(): Flow<ApiResult<List<NowPlayingSeriesApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchNowPlayingSeries()
            }, mapper = nowPlayingSeriesApiMapper
        )
    }


}