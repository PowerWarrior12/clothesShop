package com.example.adidas.utils.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

open class MenuNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager,
) : AppNavigator(activity, containerId, fragmentManager) {

    override fun applyCommand(command: Command) {
        when (command) {
            is NavigateOrOpenNew -> navigateOrOpenNew(command)
            is ToRootOrBack -> toRootOrBack(command)
            else -> super.applyCommand(command)
        }
    }

    private fun navigateOrOpenNew(
        fragmentScreen: FragmentScreen,
        tag: String,
        newFragment: Fragment?,
        currentFragment: Fragment?,
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (newFragment == null) {
            transaction.apply {
                add(containerId, fragmentScreen.createFragment(fragmentFactory), tag)
            }
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    protected open fun navigateOrOpenNew(command: NavigateOrOpenNew) {
        var currentFragment: Fragment? = null
        for (fragment in fragmentManager.fragments) {
            if (fragment.isVisible) {
                currentFragment = fragment
                break
            }
        }
        val newFragment = fragmentManager.findFragmentByTag(command.tag)
        if (newFragment != null && currentFragment != null && newFragment === currentFragment) return

        navigateOrOpenNew(command.screen, command.tag, newFragment, currentFragment)
    }

    protected open fun toRootOrBack(command: ToRootOrBack) {
        var currentFragment: Fragment? = null
        for (fragment in fragmentManager.fragments) {
            if (fragment.isVisible) {
                currentFragment = fragment
                break
            }
        }
        if (currentFragment != null && currentFragment is OnBackFragment) {
            currentFragment.back()
        }
        val rootFragment = fragmentManager.findFragmentByTag(command.tag)
        if (currentFragment != null && rootFragment != null && currentFragment === rootFragment) {
            super.back()
            return
        }

        navigateOrOpenNew(command.screen, command.tag, rootFragment, currentFragment)
    }
}