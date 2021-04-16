package com.conditer.conditercompany

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.priceTable
import com.conditer.conditercompany.databasese.priceTableDao
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_sweet -> {
                    val fragment = SweetFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cakes -> {
                    val fragment = CakesFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        val priceDAO = db.priceTableDao();


        updateListPrice(priceDAO)


        val bottomNavigationView:BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

    private fun updateListPrice(priceDAO: priceTableDao) {

        val priceItem = priceTable();
        if(priceDAO.countAllPrice == 0.toLong())
            for(i in 0..9){
                priceItem.namePrice = this.resources.getStringArray(R.array.sweetArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.sweetCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.sweetDescription)[i]
                priceItem.otdelPrice = 1;
                priceDAO.addPrice(priceItem)//добавление в базу

                priceItem.namePrice = this.resources.getStringArray(R.array.cakeArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.cakeCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.cakeDescription)[i]
                priceItem.otdelPrice =2;
                priceDAO.addPrice(priceItem)//добавление в базу тортика
            }
        else
            for(i in 0..9 step 2){
                priceItem.id_price = (i+1).toLong();
                priceItem.namePrice = this.resources.getStringArray(R.array.sweetArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.sweetCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.sweetDescription)[i]
                priceItem.otdelPrice = 1;
                priceDAO.update(priceItem)//добавление в базу

                priceItem.id_price = (i+2).toLong();
                priceItem.namePrice = this.resources.getStringArray(R.array.cakeArray)[i]
                priceItem.categoryPrice = this.resources.getStringArray(R.array.cakeCategory)[i]
                priceItem.descriptionPrice = this.resources.getStringArray(R.array.cakeDescription)[i]
                priceItem.otdelPrice =2;
                priceDAO.update(priceItem)//добавление в базу тортика

            }
    }
}