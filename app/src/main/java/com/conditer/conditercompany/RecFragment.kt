package com.conditer.conditercompany

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase
import com.conditer.conditercompany.databasese.priceTable
import java.lang.reflect.Array
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class RecFragment : Fragment() {

    private var columnCount = 1
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list_rec, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                val priceDAO = db.priceTableDao();
                val buyDAO = db.buyTableDao();
                var items = priceDAO.allPrice
                items.shuffle()
                var id_priceAndRate = buyDAO.getDistinctFromBuyTableByUI(activity!!.getSharedPreferences("prefer", Context.MODE_PRIVATE).getLong("user_id_SP",0))
                id_priceAndRate.reverse()
                id_priceAndRate = id_priceAndRate.distinctBy { it.id_price }
                id_priceAndRate.sortByDescending { it.rateBuy }

                if(id_priceAndRate.size > 0) {
                    val buyItems = emptyList<priceTable>().toMutableList()
                    for (i in 0 until id_priceAndRate.size) {
                        buyItems += priceDAO.getPriceByID(id_priceAndRate[i].id_price)
                        //buyItems[i].namePrice += id_priceAndRate[i].rateBuy.toString()
                    }

                    for((j, i) in (0..items.size step 2).withIndex()){
                        if(j>=buyItems.size){
                            break
                        }

                        items[i] = buyItems[j]
                    }

                }

                items = items.distinctBy { it.namePrice }

                adapter = MyRecRecyclerViewAdapter(context,items)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                RecFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}