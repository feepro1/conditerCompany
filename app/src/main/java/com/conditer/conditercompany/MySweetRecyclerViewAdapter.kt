package com.conditer.conditercompany

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.conditer.conditercompany.databasese.priceTable

import kotlin.coroutines.coroutineContext

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MySweetRecyclerViewAdapter(
    private val context: Context,
    private val values: List<priceTable>
) : RecyclerView.Adapter<MySweetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_sweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameSweetTW.text = item.namePrice
        holder.frameCardSweet.setOnClickListener {
            val Open_item_intent = Intent(context,Open_item::class.java)

            Open_item_intent.putExtra("id_price",item.id_price)
            Open_item_intent.putExtra("categoryPrice",item.categoryPrice)
            Open_item_intent.putExtra("descriptionPrice",item.descriptionPrice)
            Open_item_intent.putExtra("namePrice",item.namePrice)
            Open_item_intent.putExtra("otdelPrice",item.otdelPrice)
            context.startActivity(Open_item_intent)
        }
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameSweetTW: TextView = view.findViewById(R.id.nameSweetTW)
        val frameCardSweet: CardView = view.findViewById(R.id.frameCardSweet)

//        val contentView: TextView = view.findViewById(R.id.content)

//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }
}