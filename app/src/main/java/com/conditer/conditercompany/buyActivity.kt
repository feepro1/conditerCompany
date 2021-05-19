package com.conditer.conditercompany

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.buyTable
import com.conditer.conditercompany.databasese.userTable

class buyActivity : AppCompatActivity() {
//класс с эраном покупки
        lateinit var db: AppDatabase
        private lateinit var countTW :TextView
        private lateinit var summTW :TextView
        var price:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        price = intent.getLongExtra("pricePrice",-1)
//определяем view для количества и суммарной стоимости покупки
        countTW =  findViewById<TextView>(R.id.countTW)
        summTW =  findViewById<TextView>(R.id.summTW)

        findViewById<TextView>(R.id.title_buyTW).text = "Купить - " + intent.getStringExtra("namePrice")
        findViewById<TextView>(R.id.buy_categoryTW).text = intent.getStringExtra("categoryPrice")
        findViewById<TextView>(R.id.buy_priceTW).text = price.toString()

        summTW.text = price.toString()

        if(intent.getIntExtra("otdelPrice",-1) == 1){
            findViewById<ImageView>(R.id.imageView3).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_candy))
        }
    }
 //убрать один товар из покупки
    fun minusBuyBtnClick(view: View) {
        countTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()-1).toString() else 0.toString()
        summTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()*price).toString()else price.toString()
    }
//добавить один товар в покупку
    fun addBuyBtnClick(view: View) {
        countTW.text = (countTW.text.toString().toInt()+1).toString()
        summTW.text = (summTW.text.toString().toInt()+price).toString()
    }
//совершить покупку
    fun buyBtnClick(view: View) {
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
//определение интерфейса для работы с бд
        val btd = db.buyTableDao()
        val newBuy =  buyTable()
        newBuy.id_price = intent.getLongExtra("id_price",-1)
        newBuy.rateBuy = findViewById<RatingBar>(R.id.ratingBar2).rating.toLong()
        newBuy.id_user = mySharedPreferences.getLong("user_id_SP",0)

        btd.addNewBuy(newBuy)//добавление записей в таблицу покупок
        finish()//закрытие активити
    }
}
