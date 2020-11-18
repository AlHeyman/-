package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                    }

                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                }
            })
        }
    }
}








