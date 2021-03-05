package com.example.androidonkotlin.model

data class Weather(
        val city: City = getDefaultCity(),
        val temperature: Int = 0,
        val feelsLike: Int = 0
)

fun getDefaultCity() = City(cityName = "Khabarovsk", lat = 48.4827, lon = 135.084)