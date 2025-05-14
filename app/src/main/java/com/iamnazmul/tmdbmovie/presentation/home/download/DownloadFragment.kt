package com.iamnazmul.tmdbmovie.presentation.home.download

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.core.common.base.BaseFragment
import com.iamnazmul.tmdbmovie.databinding.FragmentDownloadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadFragment : BaseFragment<FragmentDownloadBinding>() {
    override fun viewBindingLayout() = FragmentDownloadBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}