package com.example.weatherforecast

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationInterface {

    @GET("weather?q=moradabad")
    fun getWeather(@Query("API_KEY")key: String) : Call<Main>


    companion object{
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        //now in the method create the retrofit object
        fun getInstance(): LocationInterface{
            val builder = Retrofit.Builder()
            builder.baseUrl(BASE_URL)
            builder.addConverterFactory(GsonConverterFactory.create())

            val retroFitObj = builder.build()
            return retroFitObj.create(LocationInterface::class.java)

        }
    }


}