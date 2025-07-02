package com.iamnazmul.tmdbmovie.data.paginsource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iamnazmul.tmdbmovie.data.apiservice.MovieApiService
import com.iamnazmul.tmdbmovie.data.mapper.DiscoverMovieApiMapper
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity

class DiscoverMoviePagingSource(
    private val apiService: MovieApiService,
    private val sortBy: String,
    private val mapper: DiscoverMovieApiMapper
) : PagingSource<Int, DiscoverMovieApiEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovieApiEntity> {
        return try {
            val page = params.key ?: 1
            val response = apiService.fetchDiscoverMovie(page, sortBy)

            val mappedList = mapper.mapFromApiResponse(response)

            LoadResult.Page(
                data = mappedList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (mappedList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DiscoverMovieApiEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}