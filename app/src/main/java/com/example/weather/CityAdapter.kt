package com.example.weather

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item_rv.view.*
import kotlinx.android.synthetic.main.weather_activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant

class CityAdapter(var list: MutableList<CityItem>) : RecyclerView.Adapter<CityAdapter.RvViev>() {

    val retrofit1 = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.metaweather.com/").build()

    val weatherClient1 = retrofit1.create(WeatherApi::class.java)


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
            ScreenData.cityItem = city
            // val context = holder.itemView.context
            // context.startActivity(Intent(context,Weather_Aclivity::class.java))

            val id = ScreenData.cityItem?.woeid
            if (id != null) {
                weatherClient1.weather_idi(id).enqueue(object : Callback<WeatherResponse> {

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        val CityTemp: WeatherResponse? = response.body()
                        if (CityTemp != null) {

                            holder.itemView.city_temp.text = CityTemp.consolidated_weather[0].the_temp.toInt().toString() + "º"

                        }
                    }
                })

            }

            var intent = Intent(holder.itemView.city.context, Weather_Aclivity::class.java)
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