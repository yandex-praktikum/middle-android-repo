package com.example.androidpracticumcustomview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.androidpracticumcustomview.R
import com.example.androidpracticumcustomview.constants.UiConstants.DURATION_ANIM_ALPHA
import com.example.androidpracticumcustomview.constants.UiConstants.DURATION_ANIM_Y_TRANSLATION

/**
 * Кастомный контейнер на основе [FrameLayout], который поддерживает анимацию добавляемых дочерних элементов.
 * Контейнер позволяет добавить максимум два дочерних элемента, к которым применяются эффекты
 * плавного появления (анимация прозрачности) и вертикального перемещения.
 *
 * ### Особенности:
 * - Максимум 2 дочерних элемента. При добавлении большего числа выбрасывается [IllegalStateException].
 * - Анимация прозрачности (fade-in) и вертикального смещения:
 *   - Первый элемент смещается вверх.
 *   - Второй элемент смещается вниз.
 * - Дочерние элементы автоматически центрируются внутри контейнера.
 *
 * ### Настройка через XML:
 * - `yp_animationDurationAlpha`: длительность анимации прозрачности (по умолчанию [DURATION_ANIM_ALPHA]).
 * - `yp_animationDurationTranslation`: длительность анимации вертикального перемещения (по умолчанию [DURATION_ANIM_Y_TRANSLATION]).
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var animationDurationAlpha: Long
    private var animationDurationTranslation: Long

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomContainer, 0, 0).apply {
            try {
                animationDurationAlpha =
                    getInt(R.styleable.CustomContainer_yp_animationDurationAlpha, DURATION_ANIM_ALPHA).toLong()
                animationDurationTranslation = getInt(
                    R.styleable.CustomContainer_yp_animationDurationTranslation,
                    DURATION_ANIM_Y_TRANSLATION
                ).toLong()
            } finally {
                recycle()
            }
        }
    }

    override fun addView(child: View) {
        if (childCount >= MAX_CHILD_COUNT) {
            throw IllegalStateException("CustomContainer может содержать максимум $MAX_CHILD_COUNT дочерних элемента.")
        }

        child.alpha = 0f
        child.translationY = (height / 2).toFloat()

        super.addView(child)
        post {
            startChildAnimation(child)
        }

    }

    private fun startChildAnimation(child: View) {
        val targetTranslationY = when (indexOfChild(child)) {
            FIRST_CHILD_INDEX -> -(height / 2.3f)
            SECOND_CHILD_INDEX -> (height / 2.3f)
            else -> 0f
        }

        child.animate()
            .alpha(1f)
            .setDuration(animationDurationAlpha)
            .withStartAction {
                child.animate().translationY(targetTranslationY)
                    .setDuration(animationDurationTranslation)
                    .setInterpolator(FastOutSlowInInterpolator())
                    .start()
            }
            .start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val childLeft = (width - childWidth) / 2
            val childTop = (height - childHeight) / 2

            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    private companion object {
        const val FIRST_CHILD_INDEX = 0
        const val SECOND_CHILD_INDEX = 1
        const val MAX_CHILD_COUNT = 2
    }
}
