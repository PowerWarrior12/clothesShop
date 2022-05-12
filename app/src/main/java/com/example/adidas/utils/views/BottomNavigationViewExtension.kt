package com.example.adidas.utils.views

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setItemSelected(itemId: Int) {
    menu.findItem(itemId).isChecked = true
}