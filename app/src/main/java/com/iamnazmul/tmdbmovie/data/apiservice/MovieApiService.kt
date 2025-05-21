package com.iamnazmul.tmdbmovie.data.apiservice
import com.iamnazmul.tmdbmovie.model.response.MovieApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovie() : Response<MovieApiResponse>

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovie(
        @Query("page") page : Int
    ) : Response<MovieApiResponse>

    @GET("tv/on_the_air")
    suspend fun fetchNowPlayingSeries() : Response<MovieApiResponse>
}