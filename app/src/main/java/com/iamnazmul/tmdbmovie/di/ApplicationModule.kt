package com.iamnazmul.tmdbmovie.di

import android.content.Context
import com.iamnazmul.tmdbmovie.BuildConfig
import com.iamnazmul.tmdbmovie.common.sharedpreference.SharedPrefHelper
import com.iamnazmul.tmdbmovie.common.utils.Utils
import com.iamnazmul.tmdbmovie.di.aurefresh.AuthRefreshServiceHolder
import com.iamnazmul.tmdbmovie.di.qualifier.AppBuildType
import com.iamnazmul.tmdbmovie.di.qualifier.AppVersion
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun sharePrefHelper(@ApplicationContext context: Context): SharedPrefHelper =
        SharedPrefHelper(context)


    @Provides
    @Singleton
    fun provideAuthRefreshServiceHolder() : AuthRefreshServiceHolder =
        AuthRefreshServiceHolder()

    @Provides
    @Singleton
    @AppBuildType
    fun provideBuildType() = Utils.getBuildTypeName(BuildConfig.BUILD_TYPE)

    @Provides
    @Singleton
    @AppVersion
    fun provideVersion() = BuildConfig.VERSION_NAME
}