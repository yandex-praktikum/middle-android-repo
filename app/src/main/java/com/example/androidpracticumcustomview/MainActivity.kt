package com.example.androidpracticumcustomview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidpracticumcustomview.activities.Screen2Activity
import com.example.androidpracticumcustomview.activities.Screen3Activity
import com.example.androidpracticumcustomview.ui.MainScreen

/**
 * Основная активность приложения, представляющая главный экран.
 *
 * В этой активности создаются и настраиваются кнопки для перехода на другие экраны приложения.
 * При нажатии на кнопки происходит запуск соответствующих активностей:
 * - Переход на второй экран (Screen2Activity)
 * - Переход на третий экран (Screen3Activity)
 *
 * Также активность управляет UI с помощью компонента Jetpack Compose `MainScreen`,
 * который отображает две кнопки с текстом, загружаемым из ресурсов.
 *
 * @see MainScreen для отображения UI и навигации.
 * @see Screen2Activity для второго экрана.
 * @see Screen3Activity для третьего экрана.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firstButtonText = getString(R.string.go_to_second_screen)
        val secondButtonText = getString(R.string.go_to_third_screen)
        setContent {
            MainScreen(
                onNavigateToScreen2 = {
                    startActivity(Screen2Activity.newIntent(this))
                },
                onNavigateToScreen3 = {
                    startActivity(Screen3Activity.newIntent(this))
                },
                firsButtonText = firstButtonText,
                secondButtonText = secondButtonText
            )
        }
    }
}