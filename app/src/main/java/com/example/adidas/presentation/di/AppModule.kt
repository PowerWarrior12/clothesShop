package com.example.adidas.presentation.di

import dagger.Module

@Module(includes = [NavigationModule::class, LocalNavigationModule::class])
class AppModule