package com.example.weather

import java.text.SimpleDateFormat
import java.util.*

data class WeatherItem(
    var humidity: Float,
    var visibility: Float,
    var weather_state_name: String,
    val the_temp: Double,
    var wind_speed: Float,

    var max_temp: Double,
    var min_temp: Double,
    var air_pressure: Float
    //var sun_rise: String
    //var sun_set : String
)
