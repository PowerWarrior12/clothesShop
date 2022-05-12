package com.example.adidas.presentation.container

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adidas.R
import com.example.adidas.databinding.ContainerFragmentBinding
import com.example.adidas.presentation.MainApplication
import com.example.adidas.utils.navigation.LocalNavigationHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

private const val CONTAINER_TAG = "container_tag"
private const val DEFAULT_TAG = "default_tag"

class ContainerFragment() : Fragment(R.layout.container_fragment) {
    private val containerTag
        get() = arguments?.getString(CONTAINER_TAG) ?: DEFAULT_TAG

    private val binding by viewBinding<ContainerFragmentBinding>()

    private val navigator by lazy {
        AppNavigator(requireActivity(), R.id.container, childFragmentManager)
    }

    private val containerViewModel by viewModels<ContainerViewModel> { containerViewModelFactory.create(containerTag) }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        containerViewModel.openRoot()
    }

    @Inject
    lateinit var containerViewModelFactory: ContainerViewModel.ContainerViewModelFactory.Factory

    @Inject
    lateinit var localNavigationHolder: LocalNavigationHolder

    override fun onResume() {
        super.onResume()
        localNavigationHolder.getNavigatorHolder(containerTag).setNavigator(navigator)
    }

    override fun onPause() {
        localNavigationHolder.getNavigatorHolder(containerTag).removeNavigator()
        super.onPause()
    }

    companion object {
        fun newInstance(
            fragmentScreen: FragmentScreen,
            containerTag: String,
            localNavigationHolder: LocalNavigationHolder,
        ): ContainerFragment =
            ContainerFragment().apply {
                localNavigationHolder.putRootScreen(containerTag, fragmentScreen)
                arguments = bundleOf(CONTAINER_TAG to containerTag)
            }
    }
}