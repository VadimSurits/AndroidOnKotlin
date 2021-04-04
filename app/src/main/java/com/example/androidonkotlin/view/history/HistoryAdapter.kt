package com.example.androidonkotlin.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonkotlin.R
import com.example.androidonkotlin.model.Weather
import kotlinx.android.synthetic.main.fragment_history_recyclerview_item.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<Weather> = arrayListOf()

    fun setData(data: List<Weather>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate
                        (R.layout.fragment_history_recyclerview_item, parent, false)
                        as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Weather) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.recyclerViewItem.text = String.format("%s %d %s", data.city.cityName,
                        data.temperature, data.condition)
            }
            itemView.setOnClickListener {
                Toast.makeText(
                        itemView.context,
                        "on click: ${data.city.cityName}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}