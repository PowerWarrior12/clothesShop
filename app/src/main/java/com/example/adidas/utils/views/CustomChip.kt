package com.example.adidas.utils.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.adidas.R
import com.google.android.material.chip.Chip
import com.google.android.material.shape.ShapeAppearanceModel

class CustomChip(context: Context, attrs: AttributeSet) : Chip(context, attrs) {

    init {
        isCheckable = true
        chipStrokeColor = resources.getColorStateList(R.color.categories_chip_selector, context.theme)
        chipStrokeWidth = resources.getDimension(R.dimen.border_width)
        chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
        shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(0.0f)
        setTextColor(resources.getColorStateList(R.color.categories_chip_selector, context.theme))
    }
}