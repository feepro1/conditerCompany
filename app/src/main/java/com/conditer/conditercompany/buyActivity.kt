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

        lateinit var db: AppDatabase
        private lateinit var countTW :TextView
        private lateinit var summTW :TextView
        var price:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        price = intent.getLongExtra("pricePrice",-1)

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

    fun minusBuyBtnClick(view: View) {
        countTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()-1).toString() else 0.toString()
        summTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()*price).toString()else price.toString()
    }
    fun addBuyBtnClick(view: View) {
        countTW.text = (countTW.text.toString().toInt()+1).toString()
        summTW.text = (summTW.text.toString().toInt()+price).toString()
    }

    fun buyBtnClick(view: View) {
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)

        val btd = db.buyTableDao()
        val newBuy =  buyTable()
        newBuy.id_price = intent.getLongExtra("id_price",-1)
        newBuy.rateBuy = findViewById<RatingBar>(R.id.ratingBar2).rating.toLong()
        newBuy.id_user = mySharedPreferences.getLong("user_id_SP",0)

        btd.addNewBuy(newBuy)
        finish()
    }
}