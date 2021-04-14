package com.conditer.conditercompany

import android.content.*
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity


class CheckLogin : AppCompatActivity() {
    // это будет именем файла настроек
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_NAME = "Nickname" // имя кота

    fun CheckLogin(): Boolean{
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        return mySharedPreferences.contains("Nickname")
    }
}