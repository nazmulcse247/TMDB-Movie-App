package com.iamnazmul.tmdbmovie.presentation.home.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.iamnazmul.tmdbmovie.common.adapter.DataBoundListAdapter
import com.iamnazmul.tmdbmovie.common.adapter.DataBoundPagingAdapter
import com.iamnazmul.tmdbmovie.common.extfun.format
import com.iamnazmul.tmdbmovie.common.extfun.loadImage
import com.iamnazmul.tmdbmovie.databinding.ItemDiscoverMovieBinding
import com.iamnazmul.tmdbmovie.model.entity.DiscoverMovieApiEntity
import com.muratozturk.mova.common.enums.ImageTypeEnum

class ExplorePagingAdapter :
    DataBoundPagingAdapter<DiscoverMovieApiEntity, ItemDiscoverMovieBinding>(

        diffCallback = object : DiffUtil.ItemCallback<DiscoverMovieApiEntity>() {

            override fun areItemsTheSame(
                oldItem: DiscoverMovieApiEntity,
                newItem: DiscoverMovieApiEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DiscoverMovieApiEntity,
                newItem: DiscoverMovieApiEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun createBinding(parent: ViewGroup): ItemDiscoverMovieBinding {
        return ItemDiscoverMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(
        binding: ItemDiscoverMovieBinding,
        item: DiscoverMovieApiEntity,
        position: Int
    ) {
        binding.apply {
            imageView.loadImage(
                item.posterPath,
                imageTypeEnum = ImageTypeEnum.POSTER
            )

            voteAverageTV.text = item.voteAverage.format(1)
        }
    }
}