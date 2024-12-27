package com.example.androidpracticumcustomview.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androidpracticumcustomview.R
import com.example.androidpracticumcustomview.constants.UiConstants.DURATION_ANIM_ALPHA
import com.example.androidpracticumcustomview.constants.UiConstants.DURATION_ANIM_Y_TRANSLATION
import kotlinx.coroutines.launch

/**
 * Анимированный контейнер для двух дочерних композаблов.
 *
 * @param firstChild Первый дочерний элемент. Опционален.
 * @param secondChild Второй дочерний элемент. Опционален.
 * @param animationDurationAlpha Длительность анимации прозрачности в миллисекундах. По умолчанию [DURATION_ANIM_ALPHA].
 * @param animationDurationPosition Длительность анимации вертикального смещения в миллисекундах. По умолчанию [DURATION_ANIM_Y_TRANSLATION].
 * @param childSize Размер дочернего элемента. По умолчанию 100.dp.
 * @param backgroundColor Цвет фона дочернего элемента. По умолчанию зеленый ([R.color.green]).
 * @param borderColor Цвет границы дочернего элемента. По умолчанию черный.
 * @param cornerRadius Радиус скругления углов фона и границы. По умолчанию 8.dp.
 *
 * Контейнер отображает до двух дочерних элементов, применяя к ним анимацию плавного появления
 * (fade-in) и вертикального смещения:
 * - Первый элемент смещается вверх.
 * - Второй элемент смещается вниз.
 */

@Composable
fun CustomContainerCompose(
    firstChild: @Composable (() -> Unit)? = null,
    secondChild: @Composable (() -> Unit)? = null,
    animationDurationAlpha: Int = DURATION_ANIM_ALPHA,
    animationDurationPosition: Int = DURATION_ANIM_Y_TRANSLATION,
    childSize: Dp = 100.dp,
    backgroundColor: Color = colorResource(id = R.color.green),
    borderColor: Color = Color.Black,
    cornerRadius: Dp = 8.dp
) {
    val firstChildAlpha = remember { Animatable(0f) }
    val secondChildAlpha = remember { Animatable(0f) }
    val firstChildOffsetY = remember { Animatable(0f) }
    val secondChildOffsetY = remember { Animatable(0f) }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val targetFirstOffset = -screenHeight / 2.3f
    val targetSecondOffset = screenHeight / 2.3f

    LaunchedEffect(Unit) {
        firstChild?.let {
            launch {
                    firstChildAlpha.animateTo(1f, tween(animationDurationAlpha))
            }
            launch {
                firstChildOffsetY.animateTo(targetFirstOffset, tween(animationDurationPosition))
            }
        }

        secondChild?.let {
            launch {
                secondChildAlpha.animateTo(1f, tween(animationDurationAlpha))
            }
            launch {
                secondChildOffsetY.animateTo(targetSecondOffset, tween(animationDurationPosition))
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        firstChild?.let {
            AnimatedChild(
                modifier = Modifier,
                alpha = firstChildAlpha.value,
                offsetY = firstChildOffsetY.value,
                contentAlignment = Alignment.Center,
                childSize = childSize,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                cornerRadius = cornerRadius,
                childContent = it
            )
        }

        secondChild?.let {
            AnimatedChild(
                modifier = Modifier,
                alpha = secondChildAlpha.value,
                offsetY = secondChildOffsetY.value,
                contentAlignment = Alignment.Center,
                childSize = childSize,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                cornerRadius = cornerRadius,
                childContent = it
            )
        }
    }
}

/**
 * Анимированный дочерний элемент с настройкой прозрачности, смещения, размера и стиля.
 *
 * @param modifier Модификатор для настройки Box.
 * @param alpha Прозрачность элемента (от 0f до 1f).
 * @param offsetY Вертикальное смещение элемента в dp.
 * @param contentAlignment Выравнивание содержимого внутри элемента.
 * @param childSize Размер элемента. По умолчанию 100.dp.
 * @param backgroundColor Цвет фона элемента. По умолчанию зеленый ([R.color.green]).
 * @param borderColor Цвет границы элемента. По умолчанию черный.
 * @param cornerRadius Радиус скругления углов фона и границы. По умолчанию 8.dp.
 * @param childContent Содержимое элемента, передаваемое в виде композабла.
 *
 * Компонент отображает анимированный элемент (например, с эффектами прозрачности и смещения)
 * с гибкими параметрами стиля и содержимым.
 */

@Composable
fun AnimatedChild(
    modifier: Modifier,
    alpha: Float,
    offsetY: Float,
    contentAlignment: Alignment,
    childSize: Dp = 100.dp,
    backgroundColor: Color = colorResource(id = R.color.green),
    borderColor: Color = Color.Black,
    cornerRadius: Dp = 8.dp,
    childContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .offset(y = offsetY.dp)
            .alpha(alpha)
            .size(childSize)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = contentAlignment
    ) {
        childContent()
    }
}