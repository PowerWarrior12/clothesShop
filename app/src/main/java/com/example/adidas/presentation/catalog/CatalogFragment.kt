package com.example.adidas.presentation.catalog

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adidas.R
import com.example.adidas.databinding.CatalogFragmentBinding
import com.example.adidas.utils.views.CustomBehaviour

class CatalogFragment : Fragment(R.layout.catalog_fragment) {
    private val binding by viewBinding<CatalogFragmentBinding>()
    private val catalogViewModel by viewModels<CatalogViewModel> { CatalogViewModel.CatalogViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeView()
    }

    private fun initView() {
        initSearchView()
        initSearchButton()
        initAppLayout()
    }

    private fun initAppLayout() {
        val customBehaviour = CustomBehaviour(binding.mainToolbar.searchButton, binding.searchView)
        val params = binding.appBar.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = customBehaviour
    }

    private fun initTabLayout() {
    }

    private fun initSearchButton() {
    }

    private fun initSearchView() {

    }

    private fun observeView() {
    }


    companion object {
        fun newInstance() = CatalogFragment()
    }
}