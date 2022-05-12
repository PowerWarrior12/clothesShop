package com.example.adidas.presentation.di

import com.example.adidas.utils.navigation.MenuRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone() = Cicerone.create(MenuRouter())

    @Provides
    fun provideRouter(cicerone: Cicerone<MenuRouter>): MenuRouter = cicerone.router

    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<MenuRouter>): NavigatorHolder = cicerone.getNavigatorHolder()
}