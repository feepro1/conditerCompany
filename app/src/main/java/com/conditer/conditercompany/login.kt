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

        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        if (mySharedPreferences.contains("Nickname")) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()



    }

    fun loginBtnClick(view: View) {

        val login = findViewById<EditText>(R.id.loginET).text.toString()
        val pass = findViewById<EditText>(R.id.passET).text.toString()

        if(login.isBlank() || pass.isBlank()){
            Toast.makeText(this,"Введите данные",Toast.LENGTH_SHORT).show()
            return}

        val utd = db.userTableDao()
        val checkUser =  userTable()

        val user_id = utd?.userIsReal(login,pass)
        if(user_id!! > 0){
            val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mySharedPreferences.edit()
            editor.putString("Nickname",utd?.getUserById(user_id).name)
            editor.apply()
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Пользователь не найден",Toast.LENGTH_SHORT).show()
        }
    }

    fun registerBtn(view: View) {
        startActivity(Intent(applicationContext,Registration::class.java))
    }
}