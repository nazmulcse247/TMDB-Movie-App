package com.iamnazmul.tmdbmovie.presentation.home.explore

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.iamnazmul.tmdbmovie.common.adapter.LoadStateAdapter
import com.iamnazmul.tmdbmovie.common.extfun.autoCleared
import com.iamnazmul.tmdbmovie.common.extfun.setUpGridRecyclerView
import com.iamnazmul.tmdbmovie.common.utils.ErrorUiHandler
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.getValue

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override fun viewBindingLayout() = FragmentExploreBinding.inflate(layoutInflater)

    private lateinit var errorHandler: ErrorUiHandler
    private val viewModel by viewModels<ExploreViewModel>()

    private var exploreAdapter by autoCleared<ExplorePagingAdapter>()

    override fun initializeView(savedInstanceState: Bundle?) {
        errorHandler = ErrorUiHandler(binding.errorUi, binding.itemRv)
        initRecyclerView()
        observeMovies()
        observePagingState()

    }

    private fun initRecyclerView() {
        exploreAdapter = ExplorePagingAdapter()
        requireContext().setUpGridRecyclerView(binding.itemRv, exploreAdapter, 2)
        binding.itemRv.adapter =
            exploreAdapter.withLoadStateFooter(
                footer = LoadStateAdapter {
                    exploreAdapter.retry()
                }
            )
    }

    private fun observeMovies() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.discoverMovies.collectLatest {

                exploreAdapter.submitData(it)
            }
        }
    }

    private fun observePagingState() {

        viewLifecycleOwner.lifecycleScope.launch {

            exploreAdapter.loadStateFlow.collectLatest { loadStates ->

                when (loadStates.refresh) {

                    is LoadState.Loading -> {
                        errorHandler.showProgressBar(true)
                    }

                    is LoadState.NotLoading -> {
                        errorHandler.showProgressBar(false)
                    }

                    is LoadState.Error -> {

                        showToastMessage(
                            (loadStates.refresh as LoadState.Error)
                                .error
                                .localizedMessage ?: "Error"
                        )
                    }
                }
            }
        }
    }

}