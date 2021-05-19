package com.conditer.conditercompany

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.conditer.conditercompany.databasese.AppDatabase

/**
 * корневой фрагмент для списка тортов 
 */
class CakesFragment : Fragment() {

    private var columnCount = 1
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cakes, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
               } 
//передача в адаптер объектов из бд
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                val priceDAO = db.priceTableDao();
                val items = priceDAO.getAllPriceFromOtdel(2);
                adapter = MyCakesRecyclerViewAdapter(context,items)
            }
        }
        return view
    }
//определение параметров и количества столбцов в списке 
    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            CakesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
