package com.conditer.conditercompany

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.userTable


//import com.conditer.fabrika.database.AppDatabase


class login() : AppCompatActivity() {
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//проверка, храниться ли в кеше данные о том что пользователь уже вошёл 
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        if (mySharedPreferences.contains("user_id_SP")) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
//отключение ночной темы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
 //Получение экземпляра базы данных    
   db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()



    }
//listener на нажатие кнопки
    fun loginBtnClick(view: View) {

        val login = findViewById<EditText>(R.id.loginET).text.toString()
        val pass = findViewById<EditText>(R.id.passET).text.toString()

        if(login.isBlank() || pass.isBlank()){
            Toast.makeText(this,"Введите данные",Toast.LENGTH_SHORT).show()
            return}
//экземпляры для работы с бд
        val utd = db.userTableDao()
        val checkUser =  userTable()
//проверка существования пользователя в бд. 
//если логин выполнен сохранение его id в кеш
        val user_id = utd?.userIsReal(login,pass)
        if(user_id!! > 0){
            val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mySharedPreferences.edit()
            editor.putLong("user_id_SP",user_id)
            editor.apply()
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Пользователь не найден",Toast.LENGTH_SHORT).show()
        }
    }
//нажатие кнопки "регистрация" 
    fun registerBtn(view: View) {
        startActivity(Intent(applicationContext,Registration::class.java))
    }
}
