package com.conditer.conditercompany

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.conditer.conditercompany.databasese.priceTable


/**
*адаптер отображающий торты списком
 */
class MyCakesRecyclerViewAdapter(
    private val context: Context,
    private val values: List<priceTable>
) : RecyclerView.Adapter<MyCakesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_cakes, parent, false)
        return ViewHolder(view)
    }
// определяем для каждого эллемента списка данные 
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameCakeTW.text = item.namePrice
//слушатель на нажатия каждому эллементу списка
        holder.frameCardCake.setOnClickListener {
            val Open_item_intent = Intent(context,Open_item::class.java)

            Open_item_intent.putExtra("id_price",item.id_price)
                .putExtra("categoryPrice",item.categoryPrice)
                .putExtra("descriptionPrice",item.descriptionPrice)
                .putExtra("namePrice",item.namePrice)
                .putExtra("otdelPrice",item.otdelPrice)
                .putExtra("pricePrice",item.pricePrice)
            context.startActivity(Open_item_intent)//открыть активити с подробностями о товаре
        }
    }

    override fun getItemCount(): Int = values.size
//определение редактируемых view в списке
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameCakeTW: TextView = view.findViewById(R.id.nameCakeTW)
        val frameCardCake: CardView = view.findViewById(R.id.frameCardCake)
    }
}
