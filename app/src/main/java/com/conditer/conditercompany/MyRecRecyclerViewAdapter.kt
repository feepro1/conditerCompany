package com.conditer.conditercompany

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.conditer.conditercompany.databasese.priceTable


//адаптер отображающий рекомендации списком
class MyRecRecyclerViewAdapter(
    private val context: Context,
    private val values: List<priceTable>
)
    : RecyclerView.Adapter<MyRecRecyclerViewAdapter.ViewHolder>() {
//разные типы отображения для конфет и тортов
    val SWEET_VIEW_TYPE = 1;
    val CAKE_VIEW_TYPE = 2;

    override fun getItemViewType(position: Int): Int {

        if(values[position].otdelPrice.toInt() == SWEET_VIEW_TYPE) return SWEET_VIEW_TYPE
        return CAKE_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = if(viewType == SWEET_VIEW_TYPE)
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_item_sweet,
                            parent,
                            false
                        )
                    else
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_item_cakes,
                            parent,
                            false
                        )
        return ViewHolder(view,viewType)
    }
//заполение view каждого еллемента данными
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameTW.text = item.namePrice
        holder.frameCard.setOnClickListener {
            val Open_item_intent = Intent(context, Open_item::class.java)

            Open_item_intent.putExtra("id_price", item.id_price)
                .putExtra("categoryPrice", item.categoryPrice)
                .putExtra("descriptionPrice", item.descriptionPrice)
                .putExtra("namePrice", item.namePrice)
                .putExtra("otdelPrice", item.otdelPrice)
                .putExtra("pricePrice",item.pricePrice)
            context.startActivity(Open_item_intent)
        }
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size
//определение необходимых для редактирования полей
    inner class ViewHolder(view: View,viewType: Int) : RecyclerView.ViewHolder(view) {

        var nameTW: TextView
        var frameCard: CardView

        init {
            if(viewType == SWEET_VIEW_TYPE) {
                nameTW = view.findViewById(R.id.nameSweetTW);
                frameCard = view.findViewById(R.id.frameCardSweet)
            }else{
                nameTW = view.findViewById(R.id.nameCakeTW);
                frameCard = view.findViewById(R.id.frameCardCake)
            }
        }

//        val idView: TextView = view.findViewById(R.id.item_number)
//        val contentView: TextView = view.findViewById(R.id.content)
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }
}
