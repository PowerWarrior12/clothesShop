package com.example.adidas.presentation.container

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adidas.utils.Result
import com.example.adidas.utils.navigation.LocalNavigationHolder
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private val TAG = ContainerViewModel::class.simpleName

class ContainerViewModel(
    private val localNavigationHolder: LocalNavigationHolder,
    private val containerTag: String): ViewModel() {

    fun openRoot() {
        val result = localNavigationHolder.getRootScreen(containerTag)
        if (result is Result.Success){
            localNavigationHolder.getRouter(containerTag).newRootScreen(
                result.get()
            )
        }
        else if (result is Result.Error){
            Log.d(TAG , result.get().message.toString())
        }
    }

    class ContainerViewModelFactory @AssistedInject constructor(
        private val localNavigationHolder: LocalNavigationHolder,
        @Assisted private val containerTag: String
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ContainerViewModel(localNavigationHolder, containerTag) as T

        @AssistedFactory
        interface Factory{
            fun create(@Assisted containerTag: String): ContainerViewModelFactory
        }
    }
}