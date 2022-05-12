package com.example.adidas.presentation

import android.app.Application
import com.example.adidas.presentation.di.AppComponent
import com.example.adidas.presentation.di.DaggerAppComponent

private const val APPLICATION_COMPONENT_NOT_INITIALIZED = "Application component isn't initialized"

class MainApplication: Application() {

    private var appComponent_: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(appComponent_) {
            APPLICATION_COMPONENT_NOT_INITIALIZED
        }

    override fun onCreate() {
        super.onCreate()
        appComponent_ = DaggerAppComponent.create()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: MainApplication
            private set
    }
}