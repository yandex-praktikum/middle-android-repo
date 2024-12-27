package com.example.androidpracticumcustomview.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidpracticumcustomview.R
import com.example.androidpracticumcustomview.ui.CustomContainer

/**
 * Экран 2 (Screen2Activity), который демонстрирует использование кастомного контейнера и элементов интерфейса.
 *
 * В этом экране создается экземпляр кастомного контейнера `CustomContainer`, в который добавляются два
 * элемента `TextView` с текстом, заданным через ресурсы. Эти элементы отображаются в кастомном контейнере с
 * предустановленным стилем и фоновым изображением.
 *
 * Основные этапы работы:
 * 1. Инициализация кастомного контейнера `CustomContainer` и установка его в качестве контента экрана.
 * 2. Создание двух текстовых элементов с заданными размерами и текстом.
 * 3. Добавление созданных элементов в кастомный контейнер.
 *
 * Для создания элементов текста используется функция `createCustomTextView`, которая задает стиль и фон.
 *
 * @see CustomContainer для более подробной информации о кастомном контейнере.
 * @see TextView для получения информации о классе TextView, используемого для отображения текста.
 */

class Screen2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                startXmlPracticum()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)

        val firstView = createCustomTextView(R.string.first_element)
        val secondView = createCustomTextView(R.string.second_element)

        customContainer.addView(firstView)
        customContainer.addView(secondView)
    }

    private fun createCustomTextView(textResId: Int) =
        TextView(this).apply {
            layoutParams = ViewGroup.LayoutParams(100.dp, 100.dp)
            text = context.getString(textResId)
            textSize = 20f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            background = createBackgroundDrawable()
        }


    private fun createBackgroundDrawable() = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 16f
            setColor(ContextCompat.getColor(this@Screen2Activity, R.color.green))
            setStroke(4, Color.BLACK)
        }


    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()

    companion object {
        /**
         * Статический метод для создания нового интента, который запускает [Screen2Activity].
         *
         * @param context Контекст для создания нового интента.
         * @return Новый интент для запуска `Screen2Activity`.
         */
        fun newIntent(context: Context) = Intent(context, Screen2Activity::class.java)
    }
}