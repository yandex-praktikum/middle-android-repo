package com.example.androidpracticumcustomview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.androidpracticumcustomview.R

/**
 * Главный экран, содержащий две кнопки для навигации.
 *
 * @param onNavigateToScreen2 Функция для перехода на второй экран при нажатии на первую кнопку.
 * @param onNavigateToScreen3 Функция для перехода на третий экран при нажатии на вторую кнопку.
 * @param firsButtonText Текст на первой кнопке.
 * @param secondButtonText Текст на второй кнопке.
 *
 * Отображает две кнопки для перехода на другие экраны. Каждая кнопка запускает соответствующую
 * навигационную функцию при нажатии.
 */

@Composable
fun MainScreen(
    onNavigateToScreen2: () -> Unit,
    onNavigateToScreen3: () -> Unit,
    firsButtonText: String,
    secondButtonText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavigationButton(
            text = firsButtonText,
            onClick = onNavigateToScreen2
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        NavigationButton(
            text = secondButtonText,
            onClick = onNavigateToScreen3
        )
    }
}

/**
 * Кнопка для навигации с заданным текстом и обработчиком клика.
 *
 * @param text Текст, отображаемый на кнопке.
 * @param onClick Функция, вызываемая при нажатии на кнопку.
 *
 * Создает кнопку с заданным текстом и цветами, которая вызывает функцию `onClick` при нажатии.
 */


@Composable
fun NavigationButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.green),
            contentColor = Color.White
        )
    ) {
        Text(text = text, color = Color.White)
    }
}