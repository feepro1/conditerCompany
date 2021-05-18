package com.conditer.conditercompany

import kotlin.random.Random.Default.nextInt
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.priceTable
import com.conditer.conditercompany.databasese.priceTableDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.random.Random.Default.nextLong


class MainActivity : AppCompatActivity() {
//экземпляр бд
    lateinit var db: AppDatabase
//listener нажатий на нижней панели навигации
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {//переход на страницу с конфетами

                R.id.navigation_sweet -> {
                    val fragment = SweetFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                //переход на страницу с тортами
                R.id.navigation_cakes -> {
                    val fragment = CakesFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                //переход на страницу рекомендаций
                R.id.navigation_rec -> {
                    val fragment = RecFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
 //при создании экрана
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //получение экземпляра бд
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        //инициализация интерфейса для редактирования прайся
        val priceDAO = db.priceTableDao();


        updateListPrice(priceDAO)

        //вешаем слушатель на панель навигации
        val bottomNavigationView:BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //открываем первым экраном рекомендации 
        val fragment = RecFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                .commit()

    }
//обновление базы из кеша(предполагается что данные должны быть на сервере

    private fun updateListPrice(priceDAO: priceTableDao) {

        val priceItem = priceTable();
        if(priceDAO.countAllPrice == 0.toLong())
            for(i in 0..9){
                priceItem.namePrice = this.resources.getStringArray(R.array.sweetArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.sweetCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.sweetDescription)[i]
                priceItem.otdelPrice = 1;
                priceItem.pricePrice = kotlin.random.Random.Default.nextLong(100,600)
                priceDAO.addPrice(priceItem)//добавление в базу конфеты

                priceItem.namePrice = this.resources.getStringArray(R.array.cakeArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.cakeCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.cakeDescription)[i]
                priceItem.otdelPrice =2;
                priceItem.pricePrice = kotlin.random.Random.Default.nextLong(100,600)
                priceDAO.addPrice(priceItem)//добавление в базу тортика
            }
    }
//кнопка выхода из системы. Чистка кеша аккаунта
    fun outButtonClick(view: View) {
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = mySharedPreferences.edit()
        editor.remove("user_id_SP")
        editor.apply()
        startActivity(Intent(applicationContext,login::class.java))
        finish()
    }
}
