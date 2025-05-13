package com.iamnazmul.tmdbmovie.presentation.home
import com.iamnazmul.tmdbmovie.domain.apiusecase.PopularMovieApiUseCase
import com.iamnazmul.tmdbmovie.domain.base.ApiResult
import com.iamnazmul.tmdbmovie.domain.base.BaseViewModel
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularMovieApiUseCase: PopularMovieApiUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading(false))
    val uiState get() = _uiState

    val action: (UiAction) -> Unit = {
        when (it) {
            UiAction.FetchPopularMovie -> fetchPopularMovie()
        }
    }

    init {
        fetchPopularMovie()
    }

    private fun fetchPopularMovie() {
        execute {
            popularMovieApiUseCase.execute().collect { result ->
                when(result) {
                    is ApiResult.Error -> _uiState.value = UiState.ApiError(result.message)
                    is ApiResult.Loading -> _uiState.value = UiState.Loading(result.loading)
                    is ApiResult.Success -> _uiState.value = UiState.ApiSuccess(result.data)
                }
            }
        }
    }
}

sealed interface UiState {
    data class Loading(val isLoading: Boolean) : UiState
    data class ApiError(val message: String) : UiState
    data class ApiSuccess(val menu: List<PopularMovieApiEntity>) : UiState
}

sealed interface UiAction {
    data object FetchPopularMovie : UiAction
}