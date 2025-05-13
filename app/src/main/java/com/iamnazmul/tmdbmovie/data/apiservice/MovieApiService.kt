package com.iamnazmul.tmdbmovie.data.apiservice

import com.iamnazmul.tmdbmovie.model.response.PopularMovieApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovie() : Response<PopularMovieApiResponse>
}