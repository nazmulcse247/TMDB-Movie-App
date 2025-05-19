package com.iamnazmul.tmdbmovie.data.repoimpl.remote

import com.iamnazmul.tmdbmovie.data.NetworkBoundResource
import com.iamnazmul.tmdbmovie.data.apiservice.MovieApiService
import com.iamnazmul.tmdbmovie.data.mapper.NowPlayingMovieApiMapper
import com.iamnazmul.tmdbmovie.data.mapper.PopularMovieApiMapper
import com.iamnazmul.tmdbmovie.data.mapper.mapFromApiResponse
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.repository.MovieRepository
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val popularMovieApiMapper: PopularMovieApiMapper,
    private val nowPlayingMovieApiMapper: NowPlayingMovieApiMapper,
    private val networkBoundResources: NetworkBoundResource
) : MovieRepository{
    override suspend fun fetchPopularMovie(): Flow<ApiResult<List<PopularMovieApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchPopularMovie()
            }, mapper = popularMovieApiMapper
        )
    }

    override suspend fun fetchNowPlayingMovie(page: Int): Flow<ApiResult<NowPlayingMovieApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchNowPlayingMovie(page)
            } , mapper = nowPlayingMovieApiMapper
        )
    }


}