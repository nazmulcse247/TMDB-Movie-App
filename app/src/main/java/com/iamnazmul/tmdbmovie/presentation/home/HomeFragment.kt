package com.iamnazmul.tmdbmovie.presentation.home
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.iamnazmul.tmdbmovie.common.utils.ErrorUiHandler
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    private lateinit var errorHandler: ErrorUiHandler

    private val viewModel by viewModels<HomeViewModel>()

    override fun viewBindingLayout() = FragmentHomeBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        errorHandler = ErrorUiHandler(binding.errorUi, binding.featureUi)
        popularMovieStateObserver()
    }

    private fun popularMovieStateObserver() {
        viewModel.uiState.execute { state ->
            when (state) {
                is UiState.ApiError -> errorHandler.dataError(state.message) {
                    viewModel.action(UiAction.FetchPopularMovie)
                }

                is UiState.ApiSuccess -> {
                    Timber.e("API_CALL: ${state.menu.size}")
                }

                is UiState.Loading -> errorHandler.showProgressBar(state.isLoading)
            }
        }
    }

}