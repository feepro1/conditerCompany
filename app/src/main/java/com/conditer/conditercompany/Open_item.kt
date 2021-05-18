package com.conditer.conditercompany

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlin.random.Random

class Open_item : AppCompatActivity() {
    var price:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_item)
        findViewById<TextView>(R.id.name_openItemTW).text = intent.getStringExtra("namePrice")
        findViewById<TextView>(R.id.description_openItemTW).text = intent.getStringExtra("descriptionPrice")
//получение переданных на этот экрсн данных
        price = intent.getLongExtra("pricePrice",-1)//Random.nextInt(100,600).toString()
        findViewById<TextView>(R.id.saleRandomTW).text = "Цена ${price}Р"

        if(intent.getIntExtra("otdelPrice",-1) == 1){
            findViewById<ImageView>(R.id.imageView2).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_candy))
        }

    }
//закрыть экран
    fun backBtnClick(view: View) {
        finish()
    }
//нажатие кнопки купить
    fun buy_openItemBtnClick(view: View) {
        //добавление данных в новый экран и его запуса
      val buyIntent = Intent(applicationContext,buyActivity::class.java)
        buyIntent
            .putExtra("price", price)
            .putExtra("id_price",intent.getLongExtra("id_price",-1))
            .putExtra("categoryPrice",intent.getStringExtra("categoryPrice"))
            .putExtra("descriptionPrice",intent.getStringExtra("descriptionPrice"))
            .putExtra("namePrice",intent.getStringExtra("namePrice"))
            .putExtra("otdelPrice",intent.getIntExtra("otdelPrice",-1))
            .putExtra("pricePrice",intent.getLongExtra("pricePrice",-1))
        startActivity(buyIntent)
    }
}
