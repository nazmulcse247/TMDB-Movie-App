package com.iamnazmul.tmdbmovie.di.module
import com.iamnazmul.tmdbmovie.data.repoimpl.remote.PopularMovieRepoImpl
import com.iamnazmul.tmdbmovie.domain.repository.PopularMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(popularMovieRepository: PopularMovieRepoImpl): PopularMovieRepository

}
