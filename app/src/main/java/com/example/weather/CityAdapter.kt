package com.example.weather

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item_rv.view.*
import java.time.Instant

class CityAdapter(var list: MutableList<CityItem>) : RecyclerView.Adapter<CityAdapter.RvViev>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViev {
        Log.d("RvAdapter", "onCreateViewHolder")
        return RvViev(
            LayoutInflater.from(parent.context).inflate(R.layout.city_item_rv, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RvViev, position: Int) {
        val city = list[position]
        holder.itemView.city.text = city.title
        holder.itemView.setOnClickListener {
            ScreenData.cityItem=city
           // val context = holder.itemView.context
           // context.startActivity(Intent(context,Weather_Aclivity::class.java))

            var intent = Intent(holder.itemView.city.context,Weather_Aclivity::class.java)
            holder.itemView.city.getContext().startActivity(intent)


        }
    }


    fun addCity(cityItem: List<CityItem>) {
        list.clear()
        list.addAll(cityItem)
        notifyDataSetChanged()
    }

    class RvViev(view: View) : RecyclerView.ViewHolder(view)

}