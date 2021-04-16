package com.conditer.conditercompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class buyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        findViewById<TextView>(R.id.title_buyTW).text = "Купить - " + intent.getStringExtra("namePrice")
        findViewById<TextView>(R.id.buy_categoryTW).text = intent.getStringExtra("categoryPrice")
        findViewById<TextView>(R.id.buy_priceTW).text= intent.getStringExtra("price")
    }
}