package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/api/location/search/")
    fun searchCity (@Query("query")query: String):Call<List<CityItem>>
}

