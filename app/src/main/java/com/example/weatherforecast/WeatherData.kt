package com.example.weatherforecast

data class WeatherData(val temp: Double, val humidity:Int, val pressure:Long)

data class Main(val main: List<WeatherData>)