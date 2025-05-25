package com.iamnazmul.tmdbmovie.presentation.home

import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchNowPlayingMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchNowPlayingSeriesApiUseCase
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchPopularMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.base.BaseViewModel
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPopularMovieApiUseCase: FetchPopularMovieApiUseCase,
    private val fetchNowPlayingMovieApiUseCase: FetchNowPlayingMovieApiUseCase,
    private val fetchNowPlayingSeriesApiUseCase: FetchNowPlayingSeriesApiUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading(false))
    val uiState get() = _uiState

    private val _nowPlayingMovieUiState =
        MutableStateFlow<NowPlayingMovieUiState>(NowPlayingMovieUiState.Loading(false))
    val nowPlayingMovieUiState get() = _nowPlayingMovieUiState

    private val _nowPlayingSeriesUiState =
        MutableStateFlow<NowPlayingSeriesUiState>(NowPlayingSeriesUiState.Loading(false))
    val nowPlayingSeriesUiState get() = _nowPlayingSeriesUiState

    val action: (UiAction) -> Unit = {
        when (it) {
            UiAction.FetchPopularMovie -> fetchPopularMovie()
            is UiAction.FetchNowPlayingMovie -> fetchNowPlayingMovie(it.page)
            UiAction.FetchNowPlayingSeries -> fetchNowPlayingSeries()
        }
    }

    init {
        fetchPopularMovie()
        fetchNowPlayingMovie(1)
        fetchNowPlayingSeries()
    }

    private fun fetchPopularMovie() {
        execute {
            fetchPopularMovieApiUseCase.execute().collect { result ->
                when (result) {
                    is ApiResult.Error -> _uiState.value = UiState.ApiError(result.message)
                    is ApiResult.Loading -> _uiState.value = UiState.Loading(result.loading)
                    is ApiResult.Success -> _uiState.value = UiState.PopularMovieList(result.data)
                }
            }
        }
    }

    private fun fetchNowPlayingMovie(page: Int) {
        execute {
            fetchNowPlayingMovieApiUseCase.execute(FetchNowPlayingMovieApiUseCase.Params(page))
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> _nowPlayingMovieUiState.value =
                            NowPlayingMovieUiState.ApiError(result.message)

                        is ApiResult.Loading -> _nowPlayingMovieUiState.value =
                            NowPlayingMovieUiState.Loading(result.loading)

                        is ApiResult.Success -> _nowPlayingMovieUiState.value =
                            NowPlayingMovieUiState.NowPlayingMovieList(result.data)
                    }
                }
        }
    }

    private fun fetchNowPlayingSeries() {
        execute {
            fetchNowPlayingSeriesApiUseCase.execute().collect { result ->
                when (result) {
                    is ApiResult.Error -> _nowPlayingSeriesUiState.value =
                        NowPlayingSeriesUiState.ApiError(result.message)

                    is ApiResult.Loading -> _nowPlayingSeriesUiState.value =
                        NowPlayingSeriesUiState.Loading(result.loading)

                    is ApiResult.Success -> _nowPlayingSeriesUiState.value =
                        NowPlayingSeriesUiState.NowPlayingSeriesList(result.data)
                }
            }
        }
    }
}

sealed interface UiState {
    data class Loading(val isLoading: Boolean) : UiState
    data class ApiError(val message: String) : UiState
    data class PopularMovieList(val popularMovie: List<MovieApiEntity>) : UiState
}

sealed interface NowPlayingMovieUiState {
    data class Loading(val isLoading: Boolean) : NowPlayingMovieUiState
    data class ApiError(val message: String) : NowPlayingMovieUiState
    data class NowPlayingMovieList(val nowPlayingMovie: List<NowPlayingMovieApiEntity>) :
        NowPlayingMovieUiState
}

sealed interface NowPlayingSeriesUiState {
    data class Loading(val isLoading: Boolean) : NowPlayingSeriesUiState
    data class ApiError(val message: String) : NowPlayingSeriesUiState
    data class NowPlayingSeriesList(val nowPlayingSeries: List<NowPlayingSeriesApiEntity>) :
        NowPlayingSeriesUiState
}

sealed interface UiAction {
    data object FetchPopularMovie : UiAction
    data class FetchNowPlayingMovie(val page: Int) : UiAction
    data object FetchNowPlayingSeries : UiAction
}