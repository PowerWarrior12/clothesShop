package com.example.adidas.presentation.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adidas.presentation.navigation.MenuNavigatorMediator
import com.example.adidas.utils.navigation.MenuRouter
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * ViewModel с для MainActivity
 * @param router Роутер для навигации с помощью Cicerone
 * @param menuNavigator Объект, связывающий bottom navigation view с навигацией
 */
class MainActivityViewModel(private val router: Router, private val menuNavigator: MenuNavigatorMediator) : ViewModel() {

    private val rootScreen = MenuItemsToScreens.getRootScreen()

    init {
        menuNavigator.setRootScreen(rootScreen.first, rootScreen.second)
    }

    fun openFirstWindow() {
        menuNavigator.openRootScreen()
    }

    fun onBackTouch() {
        menuNavigator.onBack()
    }

    /**
     * Фабрика для создания ActivityViewModel
     * @param router Роутер для навигации с помощью Cicerone
     * @param menuNavigatorFactory Фабрика для создания MenuNavigator
     * @param navigationView BottomNavigationView для передачи в menuNavigationFactory
     */
    class MainActivityViewModelFactory @AssistedInject constructor(
        private val router: MenuRouter,
        private val menuNavigatorFactory: MenuNavigatorMediator.Factory,
        @Assisted private val navigationView: BottomNavigationView,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(router,
                menuNavigatorFactory.create(navigationView, MenuItemsToScreens.getItemsToScreens())) as T
        }

        @AssistedFactory
        interface Factory {
            fun create(@Assisted navigationView: BottomNavigationView): MainActivityViewModelFactory
        }
    }
}