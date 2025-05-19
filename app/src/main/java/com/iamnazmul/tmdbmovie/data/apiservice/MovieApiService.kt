package com.iamnazmul.tmdbmovie.data.apiservice


import com.iamnazmul.tmdbmovie.model.response.NowPlayingMovieApiResponse
import com.iamnazmul.tmdbmovie.model.response.PopularMovieApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovie() : Response<PopularMovieApiResponse>

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovie(
        @Query("page") page : Int
    ) : Response<NowPlayingMovieApiResponse>
}