package com.iamnazmul.tmdbmovie.common.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.core.view.isVisible
import com.iamnazmul.tmdbmovie.R
import com.iamnazmul.tmdbmovie.databinding.ErrorUiBinding

class ErrorUiHandler(
    private var binding: ErrorUiBinding,
    private var featureUi: View? = null,
    private var context: Context? = null,
) {
    private var networkErrorCallbackFlag = 0
    private var dataErrorCallbackFlag = 0
    //private lateinit var progressDialog : KProgressHUD

    init {
        binding.root.isVisible = false
        featureUi?.isVisible = true

        /*context?.let {
            progressDialog = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Loading...")
                .setAnimationSpeed(2)
                .setCancellable(false)
                .setDimAmount(0.5f)
        }*/
    }

    fun networkError(
        networkErrorMethodFlag: Int = 0,
        networkErrorCallback: ((flag: Int) -> Unit)?
    ) {
        binding.root.background = ContextCompat.getDrawable(binding.root.context, R.color.white)
        featureUi?.isVisible = false
        binding.root.isVisible = true
        binding.errorGroup.isVisible = true
        binding.loadingBar.isVisible = false
        binding.retryButtonTv.text = binding.root.context.getString(R.string.retry)
        binding.errorTitleTV.text =
            binding.root.context.getString(R.string.msg_no_internet_connection)
        binding.errorMessageTv.text =
            binding.root.context.getString(R.string.msg_no_internet_connection)

        this.networkErrorCallbackFlag = networkErrorMethodFlag

        binding.retryButtonTv.setOnClickListener {
            reset()
            networkErrorCallback?.invoke(networkErrorCallbackFlag)
        }
    }

    fun dataError(
        message: String,
        dataErrorMethodFlag: Int = 0,
        dataErrorCallback: ((flag: Int) -> Unit)?
    ) {
        binding.root.background = ContextCompat.getDrawable(binding.root.context, R.color.white)
        featureUi?.isVisible = false
        binding.root.isVisible = true
        binding.errorGroup.isVisible = true
        binding.loadingBar.isVisible = false
        binding.errorAnimationView.setAnimation(R.raw.data_error_anim)
        binding.errorAnimationView.playAnimation()
        binding.errorTitleTV.text = binding.root.context.getString(R.string.heading_oops)
        binding.errorMessageTv.text = message

        this.dataErrorCallbackFlag = dataErrorMethodFlag
        binding.retryButtonTv.setOnClickListener {
            reset()
            dataErrorCallback?.invoke(dataErrorCallbackFlag)
        }
    }

    /*fun showLoader(isLoading: Boolean) {
        if(isLoading)
            progressDialog.show()
        else progressDialog.dismiss()
    }*/

    fun showProgressBar(isLoading: Boolean) {
        binding.root.background =
            ContextCompat.getDrawable(binding.root.context, R.color.black_opt)
        reset()
        binding.root.isVisible = isLoading
        binding.errorGroup.isVisible = !isLoading
        //lockAllChildren(isLoading)
        binding.loadingBar.isVisible = isLoading
    }

    fun showProgressBarHideFeatureUi(isLoading: Boolean) {
        binding.root.background =
            ContextCompat.getDrawable(binding.root.context, R.color.black_opt)
        binding.root.isVisible = isLoading
        featureUi?.isVisible = !isLoading
        binding.errorGroup.isVisible = !isLoading
        binding.loadingBar.isVisible = isLoading
    }

    private fun lockAllChildren(isLocked: Boolean) {
        featureUi?.allViews?.forEach {
            it.isEnabled = !isLocked
            it.isClickable = !isLocked
        }
    }

    private fun reset() {
        binding.root.isVisible = false
        featureUi?.isVisible = true
    }
}