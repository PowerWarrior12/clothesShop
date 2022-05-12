package com.example.adidas.utils.navigation

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.FragmentScreen

data class NavigateOrOpenNew(val screen: FragmentScreen, val tag: String): Command
data class ToRootOrBack(val screen: FragmentScreen, val tag: String): Command