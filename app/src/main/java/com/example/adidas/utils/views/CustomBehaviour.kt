package com.example.adidas.utils.views

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.google.android.material.appbar.AppBarLayout

class CustomBehaviour(private val targetView: View, private val dependencyView: View) : AppBarLayout.Behavior() {

    private var targetIsVisible = false
    private var lastDy = -1

    init {
        targetView.isVisible = targetIsVisible
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int,
    ): Boolean {
        return super.onStartNestedScroll(parent,
            child,
            directTargetChild,
            target,
            nestedScrollAxes,
            type) || (lastDy > 0 && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL && !targetIsVisible)
                || (lastDy < 0 && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL && targetIsVisible)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray,
    ) {
        super.onNestedScroll(coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed)
        lastDy = dyConsumed
        work(target, coordinatorLayout)
    }

    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        work(target, coordinatorLayout)
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    private fun work(target: View, coordinatorLayout: CoordinatorLayout){
        if (target.top < coordinatorLayout.top && !targetIsVisible) {
            targetIsVisible = true
            targetView.isVisible = targetIsVisible
        } else if (target.top >= coordinatorLayout.top && targetIsVisible) {
            targetIsVisible = false
            targetView.isVisible = targetIsVisible
        }
    }
}