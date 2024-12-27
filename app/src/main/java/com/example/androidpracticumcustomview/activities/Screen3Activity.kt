package com.example.androidpracticumcustomview.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.androidpracticumcustomview.R
import com.example.androidpracticumcustomview.ui.CustomContainerCompose

/**
 * Экран 3 (Screen3Activity), который отображает два текста внутри кастомного контейнера.
 *
 * В данной активности создаются два текстовых элемента с использованием стилей и размещаются
 * внутри пользовательского контейнера `CustomContainerCompose`. Эти элементы анимируются и отображаются
 * с текстами, заданными в ресурсах строк.
 *
 * Внешний вид экрана представлен с помощью компонента `Scaffold`, который добавляет отступы
 * и позволяет централизовать содержимое экрана. Внутри контейнера отображаются два текстовых элемента
 * с заданными стилями.
 *
 * @see CustomContainerCompose для создания и отображения кастомного контейнера с анимацией.
 */

class Screen3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstText = getString(R.string.first_element)
        val secondText = getString(R.string.second_element)

        val textStyle = TextStyle(
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        setContent {
            Scaffold { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {

                    CustomContainerCompose(
                        firstChild = {
                            Text(
                                text= firstText,
                                style = textStyle
                            )
                        },
                        secondChild = {
                            Text(
                                text = secondText,
                                style = textStyle
                            )
                        }
                    )
                }
            }
        }
    }

    companion object {
        /**
         * Статический метод для создания нового интента, который запускает [Screen3Activity].
         *
         * @param context Контекст для создания нового интента.
         * @return Новый интент для запуска `Screen3Activity`.
         */
        fun newIntent(context: Context) = Intent(context, Screen3Activity::class.java)
    }
}