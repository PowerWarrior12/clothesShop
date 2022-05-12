package com.example.adidas.utils.views

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable

fun resizeVectorDrawable(drawable: VectorDrawable): Drawable{
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 48, 48, false)
    val canvas = Canvas(resizedBitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return drawable
}