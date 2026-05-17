package com.iamnazmul.tmdbmovie.presentation.home.explore

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchDiscoverMoviePagedUseCase
import com.iamnazmul.tmdbmovie.domain.base.BaseViewModel
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val discoverMovieApiUseCase: FetchDiscoverMoviePagedUseCase
) : BaseViewModel(){

    private val _discoverMovies: MutableStateFlow<PagingData<DiscoverMovieApiEntity>> =
        MutableStateFlow(PagingData.empty())
    val discoverMovies
        get() = _discoverMovies.asStateFlow()

    init {
        getDiscoverMovies()
    }

    private fun getDiscoverMovies() {
        execute {
            discoverMovieApiUseCase("popularity.desc").cachedIn(viewModelScope).collectLatest {
                _discoverMovies.emit(it)
            }
        }
    }



}