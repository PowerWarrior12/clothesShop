package com.example.adidas.utils.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.example.adidas.utils.Result
import com.github.terrakok.cicerone.NavigatorHolder
import java.lang.Exception

private const val NOT_ROOT_SCREEN_EXCEPTION = "Map not contain root screen with needed tag"

class LocalNavigationHolder {
    private val containers = HashMap<String, Cicerone<Router>>()
    private val rootScreens = HashMap<String, FragmentScreen>()

    fun getRouter(containerTag: String): Router{
        val cicerone = getCicerone(containerTag)
        return cicerone.router
    }

    fun getNavigatorHolder(containerTag: String): NavigatorHolder{
        val cicerone = getCicerone(containerTag)
        return cicerone.getNavigatorHolder()
    }

    fun putRootScreen(tag: String, root: FragmentScreen){
        if (!rootScreens.containsKey(tag)){
            rootScreens[tag] = root
        }
    }

    fun getRootScreen(tag: String): Result<FragmentScreen> {
        return if (rootScreens.containsKey(tag))
            Result.Success(rootScreens[tag]!!)
        else
            Result.Error(Exception(NOT_ROOT_SCREEN_EXCEPTION))

    }

    private fun getCicerone(tag: String): Cicerone<Router> =
        containers.getOrPut(tag){
            Cicerone.create(Router())
        }
}