package com.conditer.conditercompany

import android.content.*
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity


class CheckLogin : AppCompatActivity() {
    // проверка в кеше вошел ли пользователь в систему
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_NAME = "Nickname"

    fun CheckLogin(): Boolean{
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        return mySharedPreferences.contains("Nickname")
    }
}
