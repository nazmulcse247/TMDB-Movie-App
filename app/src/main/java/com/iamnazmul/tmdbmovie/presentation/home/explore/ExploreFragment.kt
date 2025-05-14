package com.iamnazmul.tmdbmovie.presentation.home.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override fun viewBindingLayout() = FragmentExploreBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}