package com.iamnazmul.tmdbmovie

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iamnazmul.tmdbmovie.common.extfun.hideWithAnimation
import com.iamnazmul.tmdbmovie.common.extfun.hideWithoutAnimation
import com.iamnazmul.tmdbmovie.common.extfun.showWithAnimation
import com.iamnazmul.tmdbmovie.core.common.base.BaseActivity
import com.iamnazmul.tmdbmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun viewBindingLayout() = ActivityMainBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpEdgeToEdge()

        setupBottomNavigationView()

    }

    private fun setupBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)


        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                /*R.id.exploreFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.signUpFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.signInWithPasswordFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.onBoardingFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.signInWithSocialFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.dialogFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }

                R.id.videoPlayerFragment -> {
                    binding.bottomNavigation.hideWithoutAnimation(binding.fragmentContainerView)
                }*/

                R.id.myListFragment -> {
                    binding.bottomNavigation.hideWithAnimation(binding.fragmentContainerView)
                }

                else -> {
                    binding.bottomNavigation.showWithAnimation(binding.fragmentContainerView)
                }
            }
        }
    }

    private fun setUpEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        WindowInsetsControllerCompat(window, binding.root).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
    }
}