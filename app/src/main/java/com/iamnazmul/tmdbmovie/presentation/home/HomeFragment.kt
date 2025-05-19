package com.iamnazmul.tmdbmovie.presentation.home

import android.os.Bundle
import androidx.compose.runtime.saveable.autoSaver
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.common.extfun.autoCleared
import com.iamnazmul.tmdbmovie.common.extfun.setUpGridRecyclerView
import com.iamnazmul.tmdbmovie.common.extfun.setUpHorizontalRecyclerView
import com.iamnazmul.tmdbmovie.common.utils.ErrorUiHandler
import com.iamnazmul.tmdbmovie.common.utils.MyCountDownTimer
import com.iamnazmul.tmdbmovie.common.utils.parallaxPageTransformer
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentHomeBinding
import com.iamnazmul.tmdbmovie.domain.apiusecase.FetchNowPlayingMovieApiUseCase
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingMovie
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    private lateinit var errorHandler: ErrorUiHandler
    private var timer: MyCountDownTimer? = null

    private val viewModel by viewModels<HomeViewModel>()

    private var nowPlayingMovieAdapter by autoCleared<NowPlayingMovieAdapter>()

    override fun viewBindingLayout() = FragmentHomeBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        errorHandler = ErrorUiHandler(binding.errorUi, binding.featureUi)
        popularMovieStateObserver()

        viewModel.action(UiAction.FetchNowPlayingMovie(1))
    }

    private fun popularMovieStateObserver() {
        viewModel.uiState.execute { state ->
            when (state) {
                is UiState.ApiError -> errorHandler.dataError(state.message) {
                    viewModel.action(UiAction.FetchPopularMovie)
                }

                is UiState.PopularMovieList -> {
                    displaySlider(state.popularMovie)
                }

                is UiState.Loading -> errorHandler.showProgressBar(state.isLoading)
                is UiState.NowPlayingMovieList -> displayNowPlayingMovie(state.nowPlayingMovie)
            }
        }
    }

    private fun displaySlider(popularMovieList: List<PopularMovieApiEntity>) {
        val pagerAdapter =
            ViewPagerAdapter(
                popularMovieList as ArrayList<PopularMovieApiEntity>,
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

    private fun displayNowPlayingMovie(nowPlayingList : List<NowPlayingMovie>) {
        nowPlayingMovieAdapter = NowPlayingMovieAdapter()
        requireContext().setUpHorizontalRecyclerView(binding.nowPlayingMovieRv, nowPlayingMovieAdapter)

        nowPlayingMovieAdapter.submitList(nowPlayingList)
        nowPlayingMovieAdapter.notifyItemChanged(0, nowPlayingList.size)
    }

    private fun pageSwitcher(list: MutableList<PopularMovieApiEntity>) {
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