package com.iamnazmul.tmdbmovie.presentation.home

import com.iamnazmul.tmdbmovie.domain.apiusecase.PopularMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val popularMovieApiUseCase: PopularMovieApiUseCase
) : BaseViewModel(){

}