package com.iamnazmul.tmdbmovie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.iamnazmul.tmdbmovie.common.adapter.DataBoundListAdapter
import com.iamnazmul.tmdbmovie.common.extfun.format
import com.iamnazmul.tmdbmovie.common.extfun.loadImage
import com.iamnazmul.tmdbmovie.databinding.ItemNowPlayingMovieBinding
import com.iamnazmul.tmdbmovie.model.entity.MovieApiEntity
import com.muratozturk.mova.common.enums.ImageTypeEnum

class NowPlayingSeriesAdapter : DataBoundListAdapter<MovieApiEntity, ItemNowPlayingMovieBinding>(
    diffCallback = object : DiffUtil.ItemCallback<MovieApiEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieApiEntity,
            newItem: MovieApiEntity
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieApiEntity,
            newItem: MovieApiEntity
        ) = oldItem == newItem

    }
) {
    override fun createBinding(parent: ViewGroup): ItemNowPlayingMovieBinding =
        ItemNowPlayingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(
        binding: ItemNowPlayingMovieBinding,
        item: MovieApiEntity,
        position: Int
    ) {
        binding.apply {
            imageView.loadImage(item.posterPath, imageTypeEnum = ImageTypeEnum.POSTER)
            voteAverageTV.text = item.voteAverage.format(1)
        }
    }
}