package com.example.weather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_activity_main.view.*

class WeatherAdapter(var list: MutableList<WeatherItem>) :RecyclerView.Adapter<WeatherAdapter.RvViev>() {

    class RvViev(viev: View) : RecyclerView.ViewHolder(viev)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViev {
        Log.d("RvAdapter", "onCreateViewHolder")
        return RvViev(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_activity_main, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RvViev, position: Int) {
        val weather = list[position]
        //holder.itemView.proc.text = weather.visibility.toString()
        holder.itemView.temp.text = weather.the_temp.toString()
        //holder.itemView.m_h.text = weather.wind.toString()
       // holder.itemView.km.text = weather.humidity.toString()
        //holder.itemView.weather.text = weather.weather_state_abbr
    }

    fun addWeahter (weatherItem: List<WeatherItem>) {
        list.clear()
        list.addAll(weatherItem)
        notifyDataSetChanged()
    }
}

