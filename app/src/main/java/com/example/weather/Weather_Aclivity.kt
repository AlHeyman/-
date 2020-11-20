package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weather.SunRiseObj.formatter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item_rv.*
import kotlinx.android.synthetic.main.weather_activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*



class Weather_Aclivity : AppCompatActivity() {

    // private lateinit var weatherAdapter: WeatherAdapter

    val retrofit1 = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.metaweather.com/").build()

    val weatherClient1 = retrofit1.create(WeatherApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity_main)

        siti.text = ScreenData.cityItem?.title
        // var list1: MutableList<WeatherItem> = ArrayList()

        //weatherAdapter = WeatherAdapter(list1)
        // rv.adapter = weatherAdapter


        //rv.layoutManager = LinearLayoutManager(this)

        val id = ScreenData.cityItem?.woeid
        if (id != null) {
            weatherClient1.weather_idi(id).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    val weatherTemp: WeatherResponse? = response.body()
                    if (weatherTemp != null) {
                        temp.text = weatherTemp.consolidated_weather[0].the_temp.toInt().toString() + "º"

                        km.text = weatherTemp.consolidated_weather[0].humidity.toInt().toString() + "km"

                        m_h.text=weatherTemp.consolidated_weather[0].wind_speed.toInt().toString()+ "m/h"

                        proc.text=weatherTemp.consolidated_weather[0].visibility.toInt().toString()+ "%"

                        weather1.text=weatherTemp.consolidated_weather[0].weather_state_name.toString()


                        formatter.parse("sun_rise")

                        val userFormatter = SimpleDateFormat("HH:mm").format(formatter)
                        val rest = weatherTemp.consolidated_weather[0].sun_rise

                        city_temp.text = weatherTemp.consolidated_weather[0].sun_rise.format(
                            formatter).


                        var res = weatherTemp.consolidated_weather[0].air_pressure*0.7500

                        press.text=res.toString() +" mm Hg"

                        min_temp_znach.text=weatherTemp.consolidated_weather[0].min_temp.toInt().toString()+"º"

                        max_temp_znach.text=weatherTemp.consolidated_weather[0].max_temp.toInt().toString()+"º"
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                }
            })
        }
    }
}










