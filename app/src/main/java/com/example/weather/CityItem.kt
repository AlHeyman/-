  package com.example.weather

data class CityItem (
    val title: String,
    val location_type: String,
    val latt_long: String,
    val woeid: Int,
    val distance: Int
)
