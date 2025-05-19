package com.iamnazmul.tmdbmovie.presentation.home
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchNowPlayingMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchPopularMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.base.BaseViewModel
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovie
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovieApiEntity
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPopularMovieApiUseCase: FetchPopularMovieApiUseCase,
    private val fetchNowPlayingMovieApiUseCase: FetchNowPlayingMovieApiUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading(false))
    val uiState get() = _uiState

    val action: (UiAction) -> Unit = {
        when (it) {
            UiAction.FetchPopularMovie -> fetchPopularMovie()
            is UiAction.FetchNowPlayingMovie -> fetchNowPlayingMovie(it.page)
        }
    }

    init {
        fetchPopularMovie()
    }

    private fun fetchPopularMovie() {
        execute {
            fetchPopularMovieApiUseCase.execute().collect { result ->
                when(result) {
                    is ApiResult.Error -> _uiState.value = UiState.ApiError(result.message)
                    is ApiResult.Loading -> _uiState.value = UiState.Loading(result.loading)
                    is ApiResult.Success -> _uiState.value = UiState.PopularMovieList(result.data)
                }
            }
        }
    }

    private fun fetchNowPlayingMovie(page : Int) {
        execute {
            fetchNowPlayingMovieApiUseCase.execute(FetchNowPlayingMovieApiUseCase.Params(page)).collect { result ->
                when(result) {
                    is ApiResult.Error -> _uiState.value = UiState.ApiError(result.message)
                    is ApiResult.Loading -> _uiState.value = UiState.Loading(result.loading)
                    is ApiResult.Success -> _uiState.value = UiState.NowPlayingMovieList(result.data.nowPlayingMovie)
                }
            }
        }
    }
}

sealed interface UiState {
    data class Loading(val isLoading: Boolean) : UiState
    data class ApiError(val message: String) : UiState
    data class PopularMovieList(val popularMovie: List<PopularMovieApiEntity>) : UiState
    data class NowPlayingMovieList(val nowPlayingMovie: List<NowPlayingMovie>) : UiState
}

sealed interface UiAction {
    data object FetchPopularMovie : UiAction
    data class FetchNowPlayingMovie(val page : Int) : UiAction
}