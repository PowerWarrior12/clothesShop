package com.example.adidas.presentation.di

import com.example.adidas.utils.navigation.LocalNavigationHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalNavigationModule {
    @Singleton
    @Provides
    fun provideLocalNavigationHolder(): LocalNavigationHolder = LocalNavigationHolder()
}