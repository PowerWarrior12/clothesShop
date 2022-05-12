package com.example.adidas.presentation.di

import com.example.adidas.presentation.container.ContainerFragment
import com.example.adidas.presentation.mainActivity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: ContainerFragment)
}