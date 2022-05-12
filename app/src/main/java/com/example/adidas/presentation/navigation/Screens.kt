package com.example.adidas.presentation.navigation

import com.example.adidas.presentation.ProductsFragment
import com.example.adidas.presentation.basket.BasketFragment
import com.example.adidas.presentation.catalog.CatalogFragment
import com.example.adidas.presentation.container.ContainerFragment
import com.example.adidas.presentation.drops.DropsFragment
import com.example.adidas.presentation.favourites.FavouritesFragment
import com.example.adidas.presentation.radar.RadarFragment
import com.example.adidas.utils.navigation.LocalNavigationHolder
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun getDropsFragmentScreen() = FragmentScreen {
        DropsFragment.newInstance()
    }

    fun getCatalogFragmentScreen() = FragmentScreen {
        CatalogFragment.newInstance()
    }

    fun getFavouritesFragmentScreen() = FragmentScreen {
        FavouritesFragment.newInstance()
    }

    fun getBasketFragmentScreen() = FragmentScreen {
        BasketFragment.newInstance()
    }

    fun getRadarFragmentScreen() = FragmentScreen {
        RadarFragment.newInstance()
    }

    fun getProductsFragmentScreen() = FragmentScreen {
        ProductsFragment()
    }

    fun getContainerFragmentScreen(
        rootFragmentScreen: FragmentScreen,
        containerTag: String,
        localNavigationHolder: LocalNavigationHolder,
    ) = FragmentScreen {
        ContainerFragment.newInstance(rootFragmentScreen, containerTag, localNavigationHolder)
    }
}