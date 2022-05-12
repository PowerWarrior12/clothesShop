package com.example.adidas.presentation.mainActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adidas.R
import com.example.adidas.databinding.MainActivityBinding
import com.example.adidas.presentation.MainApplication
import com.example.adidas.utils.navigation.MenuNavigator
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val binding: MainActivityBinding by viewBinding()
    private val activityViewModel by viewModels<MainActivityViewModel> {
        activityViewModelFactory.create(binding.mainBottomMenu)
    }
    private val navigator: Navigator = object : MenuNavigator(this, R.id.container, supportFragmentManager) {
    }
    @Inject
    lateinit var navigationHolder: NavigatorHolder
    @Inject
    lateinit var activityViewModelFactory: MainActivityViewModel.MainActivityViewModelFactory.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.INSTANCE.appComponent.inject(this)
        activityViewModel.openFirstWindow()
    }

    override fun onResume() {
        super.onResume()
        initView()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    private fun initView() {
    }

    override fun onBackPressed() {
        activityViewModel.onBackTouch()
    }
}