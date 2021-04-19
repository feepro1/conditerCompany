package com.conditer.conditercompany

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class Open_item : AppCompatActivity() {
     lateinit var price:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_item)
        findViewById<TextView>(R.id.name_openItemTW).text = intent.getStringExtra("namePrice")
        findViewById<TextView>(R.id.description_openItemTW).text = intent.getStringExtra("descriptionPrice")

        price = Random.nextInt(100,600).toString()
        findViewById<TextView>(R.id.saleRandomTW).text = "Цена ${price}Р"


    }

    fun backBtnClick(view: View) {
        finish()
    }

    fun buy_openItemBtnClick(view: View) {
      val buyIntent = Intent(applicationContext,buyActivity::class.java)
        buyIntent
            .putExtra("price", price)
            .putExtra("id_price",intent.getLongExtra("id_price",-1))
            .putExtra("categoryPrice",intent.getStringExtra("categoryPrice"))
            .putExtra("descriptionPrice",intent.getStringExtra("descriptionPrice"))
            .putExtra("namePrice",intent.getStringExtra("namePrice"))
            .putExtra("otdelPrice",intent.getLongExtra("otdelPrice",-1))
        startActivity(buyIntent)
    }
}