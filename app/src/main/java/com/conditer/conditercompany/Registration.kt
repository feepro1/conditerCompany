package com.conditer.conditercompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.userTable
import com.conditer.conditercompany.databasese.userTableDao

class Registration : AppCompatActivity() {
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()
    }

    fun registerBtnClick(view: View) {
        val name = findViewById<EditText>(R.id.nameET).text
        val login = findViewById<EditText>(R.id.loginET2).text
        val pass = findViewById<EditText>(R.id.passET2).text

        val utd = db.userTableDao()
        val newUser =  userTable()

        //newUser.id = null
        newUser.name = name.toString()
        newUser.login = login.toString()
        newUser.pass = pass.toString()
        utd?.addUser(newUser)

        val newUser1 = utd?.getUserById(utd.usersCount)
        if (newUser1 != null) {
            Toast.makeText(this,"Пользватель создан",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}