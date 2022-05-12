package com.example.adidas.presentation.mainActivity

import android.util.SparseArray
import com.example.adidas.R
import com.example.adidas.presentation.navigation.Screens
import com.github.terrakok.cicerone.androidx.FragmentScreen

private const val rootItem = R.id.navigation_catalog
/**
 * Объект с набором связанных id элементов главного меню и экранами FragmentScreen, в которые они должны вести
 */
object MenuItemsToScreens {
    private val itemToScreen: SparseArray<FragmentScreen> = SparseArray()
    init {
        itemToScreen.put(R.id.navigation_catalog, Screens.getProductsFragmentScreen())
        itemToScreen.put(R.id.navigation_basket, Screens.getBasketFragmentScreen())
        itemToScreen.put(R.id.navigation_radar, Screens.getRadarFragmentScreen())
        itemToScreen.put(R.id.navigation_drops, Screens.getDropsFragmentScreen())
        itemToScreen.put(R.id.navigation_favourites, Screens.getFavouritesFragmentScreen())
    }
    fun getItemsToScreens() = itemToScreen

    /**
     * Возвращает связку, где первое - id элемента меню для корневого экрана, второе - корневой экран FragmentScreen
     */
    fun getRootScreen() = Pair(rootItem, itemToScreen.get(rootItem))
}