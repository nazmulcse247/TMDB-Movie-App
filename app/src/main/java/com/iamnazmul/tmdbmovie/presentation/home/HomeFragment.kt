package com.iamnazmul.tmdbmovie.presentation.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.common.extfun.autoCleared
import com.iamnazmul.tmdbmovie.common.extfun.setUpHorizontalRecyclerView
import com.iamnazmul.tmdbmovie.common.utils.ErrorUiHandler
import com.iamnazmul.tmdbmovie.common.utils.MyCountDownTimer
import com.iamnazmul.tmdbmovie.common.utils.parallaxPageTransformer
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentHomeBinding
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    private lateinit var errorHandler: ErrorUiHandler
    private var timer: MyCountDownTimer? = null

    private val viewModel by viewModels<HomeViewModel>()

    private var nowPlayingMovieAdapter by autoCleared<NowPlayingMovieAdapter>()

    private var nowPlayingSeriesAdapter by autoCleared<NowPlayingSeriesAdapter>()

    override fun viewBindingLayout() = FragmentHomeBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        errorHandler = ErrorUiHandler(binding.errorUi, binding.featureUi)

        popularMovieStateObserver()
        nowPlayingMovieStateObserver()
        nowPlayingSeriesUiStateObserver()

        //viewModel.action(UiAction.FetchPopularMovie)
        //viewModel.action(UiAction.FetchNowPlayingMovie(1))
        //viewModel.action(UiAction.FetchNowPlayingSeries)
    }

    private fun popularMovieStateObserver() {
        viewModel.uiState.execute { state ->
            when (state) {
                is UiState.ApiError -> errorHandler.dataError(state.message) {
                    viewModel.action(UiAction.FetchPopularMovie)
                    viewModel.action(UiAction.FetchNowPlayingMovie(1))
                }

                is UiState.PopularMovieList -> {
                    binding.sliderCl.isVisible = state.popularMovie.isNotEmpty()
                    displaySlider(state.popularMovie)
                }

                is UiState.Loading -> errorHandler.showProgressBarHideFeatureUi(state.isLoading)
            }
        }
    }

    private fun nowPlayingMovieStateObserver() {
        viewModel.nowPlayingMovieUiState.execute { state ->
            when (state) {
                is NowPlayingMovieUiState.ApiError -> {
                    errorHandler.dataError(state.message) {
                        viewModel.action(UiAction.FetchPopularMovie)
                        viewModel.action(UiAction.FetchNowPlayingMovie(1))
                    }
                }
                is NowPlayingMovieUiState.Loading -> {
                    errorHandler.showProgressBar(state.isLoading)
                }
                is NowPlayingMovieUiState.NowPlayingMovieList -> {
                    binding.nowPlayingMovieCl.isVisible = state.nowPlayingMovie.isNotEmpty()
                    displayNowPlayingMovie(state.nowPlayingMovie)
                }
            }
        }
    }

    private fun nowPlayingSeriesUiStateObserver() {
        viewModel.nowPlayingSeriesUiState.execute { state ->
            when (state) {
                is NowPlayingSeriesUiState.ApiError -> {
                    errorHandler.dataError(state.message) {
                        viewModel.action(UiAction.FetchNowPlayingSeries)
                    }
                }
                is NowPlayingSeriesUiState.Loading -> errorHandler.showProgressBar(state.isLoading)

                is NowPlayingSeriesUiState.NowPlayingSeriesList -> {
                    binding.nowPlayingSeriesCl.isVisible = state.nowPlayingSeries.isNotEmpty()
                    displayNowPlayingSeries(state.nowPlayingSeries)
                }
            }
        }
    }

    private fun displaySlider(popularMovieList: List<MovieApiEntity>) {
        val pagerAdapter =
            ViewPagerAdapter(
                popularMovieList as ArrayList<MovieApiEntity>,
                onClickAddList = {

                },
                playOnClick = {

                }
            )
        binding.viewpagerPopularMovies.apply {
            setScrollDurationFactor(4)
            setPageTransformer(true, parallaxPageTransformer(R.id.movieActions))
            adapter = pagerAdapter
        }

        pageSwitcher(popularMovieList)


        binding.pageCounterText.text = getString(
            R.string.format_slider_number,
            "${binding.viewpagerPopularMovies.currentItem + 1}",
            "${pagerAdapter.count}"
        )

        binding.viewpagerPopularMovies.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.pageCounterText.text = getString(
                    R.string.format_slider_number,
                    "${position + 1}",
                    "${pagerAdapter.count}"
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun displayNowPlayingMovie(nowPlayingMovieList : List<MovieApiEntity>) {
        nowPlayingMovieAdapter = NowPlayingMovieAdapter()
        requireContext().setUpHorizontalRecyclerView(binding.nowPlayingMovieRv, nowPlayingMovieAdapter)

        nowPlayingMovieAdapter.submitList(nowPlayingMovieList)
        nowPlayingMovieAdapter.notifyItemChanged(0, nowPlayingMovieList.size)
    }

    private fun displayNowPlayingSeries(nowPlayingSeriesList : List<MovieApiEntity>) {
        nowPlayingSeriesAdapter = NowPlayingSeriesAdapter()
        requireContext().setUpHorizontalRecyclerView(binding.nowPlayingSeriesRv, nowPlayingSeriesAdapter)

        nowPlayingSeriesAdapter.submitList(nowPlayingSeriesList)
        nowPlayingSeriesAdapter.notifyItemChanged(0, nowPlayingSeriesList.size)
    }

    private fun pageSwitcher(list: MutableList<MovieApiEntity>) {
        with(binding) {
            timer = MyCountDownTimer(5000, 5000) {
                try {
                    if (list.size - 1 == viewpagerPopularMovies.currentItem) viewpagerPopularMovies.currentItem =
                        0
                    else viewpagerPopularMovies.currentItem = viewpagerPopularMovies.currentItem + 1
                    timer!!.start()
                } catch (t: Throwable) {
                    timer!!.cancel()
                }
            }
            timer!!.start()
        }

    }

}