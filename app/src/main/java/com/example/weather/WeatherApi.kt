package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  WeatherApi {
    @GET("/api/location/{woeid}")
    fun weather_idi (@Path("woeid")woeid:Int): Call<WeatherResponse>
}