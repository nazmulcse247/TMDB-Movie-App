package com.iamnazmul.tmdbmovie.di.module
import com.iamnazmul.tmdbmovie.data.apiservice.MovieApiService
import com.iamnazmul.tmdbmovie.di.aurefresh.AuthRefreshApiService
import com.iamnazmul.tmdbmovie.di.aurefresh.AuthRefreshServiceHolder
import com.iamnazmul.tmdbmovie.di.qualifier.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideMovieApiService(
        @AppBaseUrl retrofit: Retrofit,
        authRefreshServiceHolder: AuthRefreshServiceHolder
    ): MovieApiService {
        authRefreshServiceHolder.setAuthRefreshApi(retrofit.create(AuthRefreshApiService::class.java))
        return retrofit.create(MovieApiService::class.java)
    }
}