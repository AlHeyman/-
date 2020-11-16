package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.metaweather.com/").build()

    val weatherClient = retrofit.create(WeatherApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.addTextChangedListener { text ->
            if (text != null) {
                val query = text.toString()

                weatherClient.searchCity(query).enqueue(object : Callback<List<CityItem>> {

                    override fun onResponse(
                            call: Call<List<CityItem>>,
                            response: Response<List<CityItem>>
                    ) {
                        val cityItem = response.body()
                        cityItem?.forEach{
                            Log.d("MainActivity", it.toString())
                        }

                    }

                    override fun onFailure(call: Call<List<CityItem>>, t: Throwable) {
                        println()
                    }


                })
            }
        }
    }
}


