package com.iamnazmul.tmdbmovie.presentation.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.iamnazmul.tmdbmovie.common.extfun.loadImage
import com.iamnazmul.tmdbmovie.databinding.ItemViewPagerBinding
import com.iamnazmul.tmdbmovie.model.entity.PopularMovieApiEntity
import com.muratozturk.mova.common.enums.ImageTypeEnum

class ViewPagerAdapter(
    private val itemList: ArrayList<PopularMovieApiEntity>
) : PagerAdapter() {

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {

        val itemBinding =
            ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        with(itemBinding) {
            with(itemList[position]) {

                backDrop.loadImage(backdropPath, imageTypeEnum = ImageTypeEnum.BACKDROP)

                titleTv.text = originalTitle
                addListBtn.isChecked = isBookmarked

                /*addListBtn.setOnClickListener {
                    onClickAddList?.invoke(
                        id, isBookmarked,
                        Bookmark(id, title, "", posterPath ?: "", voteAverage, MediaTypeEnum.MOVIE)
                    )
                }*/
            }
        }


        parent.addView(itemBinding.root, 0)

        return itemBinding.root
    }

    override fun getCount(): Int = itemList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == (`object` as ConstraintLayout)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as ConstraintLayout)
    }
}