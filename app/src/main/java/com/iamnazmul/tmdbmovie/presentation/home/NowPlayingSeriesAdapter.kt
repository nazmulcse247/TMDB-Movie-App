package com.iamnazmul.tmdbmovie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.iamnazmul.tmdbmovie.common.adapter.DataBoundListAdapter
import com.iamnazmul.tmdbmovie.common.extfun.format
import com.iamnazmul.tmdbmovie.common.extfun.loadImage
import com.iamnazmul.tmdbmovie.databinding.ItemNowPlayingMovieBinding
import com.iamnazmul.tmdbmovie.model.entity.NowPlayingSeriesApiEntity
import com.muratozturk.mova.common.enums.ImageTypeEnum

class NowPlayingSeriesAdapter : DataBoundListAdapter<NowPlayingSeriesApiEntity, ItemNowPlayingMovieBinding>(
    diffCallback = object : DiffUtil.ItemCallback<NowPlayingSeriesApiEntity>() {
        override fun areItemsTheSame(
            oldItem: NowPlayingSeriesApiEntity,
            newItem: NowPlayingSeriesApiEntity
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: NowPlayingSeriesApiEntity,
            newItem: NowPlayingSeriesApiEntity
        ) = oldItem == newItem

    }
) {
    override fun createBinding(parent: ViewGroup): ItemNowPlayingMovieBinding =
        ItemNowPlayingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(
        binding: ItemNowPlayingMovieBinding,
        item: NowPlayingSeriesApiEntity,
        position: Int
    ) {
        binding.apply {
            imageView.loadImage(item.posterPath, imageTypeEnum = ImageTypeEnum.POSTER)
            voteAverageTV.text = item.voteAverage.format(1)
        }
    }
}