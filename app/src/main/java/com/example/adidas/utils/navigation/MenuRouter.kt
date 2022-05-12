package com.example.adidas.utils.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MenuRouter: Router() {
    fun navigateOrOpenNew(screen: FragmentScreen, tag: String) = executeCommands(NavigateOrOpenNew(screen, tag))
    fun toRootOrBack(screen: FragmentScreen, tag: String) = executeCommands(ToRootOrBack(screen, tag))
}