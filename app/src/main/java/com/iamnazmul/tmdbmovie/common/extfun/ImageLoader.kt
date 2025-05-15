package com.iamnazmul.tmdbmovie.common.extfun

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.common.constant.AppConstant.getBackDropPath
import com.iamnazmul.tmdbmovie.common.constant.AppConstant.getFlagPath
import com.iamnazmul.tmdbmovie.common.constant.AppConstant.getPosterPath
import com.iamnazmul.tmdbmovie.common.constant.AppConstant.getYouTubePath
import com.muratozturk.mova.common.enums.ImageTypeEnum

fun ImageView.loadImage(url: String?, isBlur: Boolean? = false, imageTypeEnum: ImageTypeEnum) {

    val placeholder = when (imageTypeEnum) {
        ImageTypeEnum.BACKDROP -> R.drawable.gray_placeholder
        ImageTypeEnum.POSTER -> R.drawable.gray_placeholder
        ImageTypeEnum.YOUTUBE -> R.drawable.gray_placeholder
        ImageTypeEnum.CREDIT -> R.drawable.profile_filled
        ImageTypeEnum.LOCAL -> R.drawable.gray_placeholder
        ImageTypeEnum.FLAG -> R.drawable.gray_placeholder
    }

    url?.let {

        val urlString = when (imageTypeEnum) {
            ImageTypeEnum.BACKDROP -> getBackDropPath(url)
            ImageTypeEnum.POSTER -> getPosterPath(url)
            ImageTypeEnum.YOUTUBE -> getYouTubePath(url)
            ImageTypeEnum.CREDIT -> getPosterPath(url)
            ImageTypeEnum.LOCAL -> url
            ImageTypeEnum.FLAG -> getFlagPath(url)
        }

        if (isBlur == true) {
            Glide.with(this.context)
                .load(urlString)
                .apply(RequestOptions())
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(this.context.circularProgressDrawable())
                .error(placeholder)
                .into(this)
        } else {
            Glide.with(this.context)
                .load(urlString)
                .apply(RequestOptions())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(this.context.circularProgressDrawable())
                .error(placeholder)
                .into(this)
        }

    } ?: run {
        this.setImageResource(placeholder)
    }

}

fun Context.circularProgressDrawable(): Drawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 7f
        centerRadius = 60f
        setColorSchemeColors(
            androidx.core.content.ContextCompat.getColor(
                this@circularProgressDrawable,
                R.color.text_color
            )
        )
        start()
    }
}