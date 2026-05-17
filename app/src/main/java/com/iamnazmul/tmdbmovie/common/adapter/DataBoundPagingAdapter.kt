package com.iamnazmul.tmdbmovie.common.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

abstract class DataBoundPagingAdapter<T : Any, V : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, DataBoundViewHolder<V>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBoundViewHolder<V> {

        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataBoundViewHolder<V>,
        position: Int
    ) {

        getItem(position)?.let { item ->
            bind(holder.binding, item, position)
        }
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    protected abstract fun bind(
        binding: V,
        item: T,
        position: Int
    )
}