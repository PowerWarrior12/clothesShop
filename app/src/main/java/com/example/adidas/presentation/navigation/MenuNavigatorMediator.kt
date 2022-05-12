package com.example.adidas.presentation.navigation

import android.util.SparseArray
import androidx.core.util.containsKey
import com.example.adidas.utils.navigation.LocalNavigationHolder
import com.example.adidas.utils.navigation.MenuRouter
import com.example.adidas.utils.views.setItemSelected
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val DEFAULT_ROOT_INDEX = 0

/**
 * Связка BottomNavigationView с навигацией
 * Для нормальной работы необходимо передавать itemToScreen содержащий все имеющееся id элементов передаваемого
 * BottomNavigationView
 * @param router Роутер для навигации
 * @param navigationView BottomNavigationView
 * @param itemToScreen Словарь с набором: id элемента меню - FragmentScreen для перехода, необходим для навигации на нужный экран
 * при нажатии на соответствующий элемент меню
 */
class MenuNavigatorMediator @AssistedInject constructor(
    private val router: MenuRouter,
    private val localNavigationHolder: LocalNavigationHolder,
    @Assisted private val navigationView: BottomNavigationView,
    @Assisted private val itemToScreen: SparseArray<FragmentScreen>,
) {

    private var rootScreen: Pair<Int, FragmentScreen> =
        Pair(itemToScreen.keyAt(DEFAULT_ROOT_INDEX), itemToScreen.valueAt(DEFAULT_ROOT_INDEX))

    init {
        initNavigationView()
    }

    /**
     * @param itemId - id элемента меню, если navigationView не содержит элемента с таким id,
     * то замена корневого экрана не произойдёт
     * @param screen - Экран для установки его в качестве корневого и связки с itemId
     */
    fun setRootScreen(itemId: Int, screen: FragmentScreen) {
        if (itemToScreen.containsKey(itemId))
            rootScreen = Pair(itemId, screen)
    }

    fun openRootScreen() {
        router.navigateOrOpenNew(Screens.getContainerFragmentScreen(rootScreen.second,
            rootScreen.first.toString(),
            localNavigationHolder), rootScreen.first.toString())
        navigationView.setItemSelected(rootScreen.first)
    }

    /**
     * Переход на корневой экран меню, если уже там, то выход из программы
     */
    fun onBack() {
        if (rootScreen.first == navigationView.selectedItemId) {
            router.exit()
        } else {
            router.navigateOrOpenNew(rootScreen.second, rootScreen.first.toString())
            navigationView.setItemSelected(rootScreen.first)
        }
    }

    private fun initNavigationView() {
        navigationView.setOnItemSelectedListener { item ->
            if (navigationView.selectedItemId != item.itemId) {
                val neededScreen = itemToScreen.get(item.itemId)
                router.navigateOrOpenNew(Screens.getContainerFragmentScreen(neededScreen,
                    item.itemId.toString(),
                    localNavigationHolder), item.itemId.toString())
            }
            true
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted navigationView: BottomNavigationView,
            @Assisted itemToScreen: SparseArray<FragmentScreen>,
        ): MenuNavigatorMediator
    }
}