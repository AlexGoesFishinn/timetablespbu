package org.alexgoesfishinn.timetablespbu.presentation.main.utils

import android.view.View
import android.view.animation.AlphaAnimation
/**
 * @author a.bylev
 */
interface Animations {
    fun fadeIn(view: View)

    fun fadeOut(view: View)
}

class AnimationsImpl: Animations {
    override fun fadeIn(view: View) {
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 500
//            fillAfter = true
        }
        view.startAnimation(animation)
    }

    override fun fadeOut(view: View) {
        val animation = AlphaAnimation(1f, 0f).apply {
            duration = 500L
            fillAfter = true
        }
        view.startAnimation(animation)
    }
}