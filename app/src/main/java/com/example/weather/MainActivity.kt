package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var cityAdapter: CityAdapter

    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.metaweather.com/").build()

    val weatherClient = retrofit.create(CityApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var list: MutableList<CityItem> = ArrayList()

        cityAdapter= CityAdapter(list)
        rv.adapter=cityAdapter

        rv.layoutManager = LinearLayoutManager(this)

        search.addTextChangedListener { text ->
            if (text != null) {
                val query = text.toString()

                weatherClient.searchCity(query).enqueue(object : Callback<List<CityItem>> {

                    override fun onResponse(
                            call: Call<List<CityItem>>,
                            response: Response<List<CityItem>>
                    ) {
                        val cityItem = response.body()
                        if (cityItem != null) {
                           cityAdapter.addCity(cityItem)
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


