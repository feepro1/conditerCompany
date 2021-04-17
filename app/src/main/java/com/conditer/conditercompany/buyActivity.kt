package com.conditer.conditercompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class buyActivity : AppCompatActivity() {

        private lateinit var countTW :TextView
        private lateinit var summTW :TextView
        var price:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        price = intent.getStringExtra("price")!!.toInt()

        countTW =  findViewById<TextView>(R.id.countTW)
        summTW =  findViewById<TextView>(R.id.summTW)

        findViewById<TextView>(R.id.title_buyTW).text = "Купить - " + intent.getStringExtra("namePrice")
        findViewById<TextView>(R.id.buy_categoryTW).text = intent.getStringExtra("categoryPrice")
        findViewById<TextView>(R.id.buy_priceTW).text = price.toString()

        summTW.text = price.toString()
    }

    fun minusBuyBtnClick(view: View) {
        countTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()-1).toString() else 0.toString()
        summTW.text = if (countTW.text.toString().toInt()>0)(countTW.text.toString().toInt()*price).toString()else price.toString()
    }
    fun addBuyBtnClick(view: View) {
        countTW.text = (countTW.text.toString().toInt()+1).toString()
        summTW.text = (summTW.text.toString().toInt()+price).toString()
    }
}