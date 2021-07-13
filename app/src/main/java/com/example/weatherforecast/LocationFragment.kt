package com.example.weatherforecast

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationFragment : Fragment(),LocationListener {

    lateinit var search: ImageView
    lateinit var temperature:TextView
    lateinit var city:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search = view.findViewById(R.id.searchimage)
        temperature = view.findViewById(R.id.Temperture)
        city = view.findViewById(R.id.entercity)

        search.setOnClickListener{

            //here I will call the API
            temperature.text = getLocation().toString()

            getLocation()
        }

    }

    fun getLocation(){

        val apikey =resources.getString(R.string.API_KEY)

        //created the request
        val request = LocationInterface.getInstance().getWeather(apikey)
        //execution with Asynchronously with this method name
        request.enqueue(WeatherDataCallback())//request is executed

    }

    inner class WeatherDataCallback : Callback<Main> {
        override fun onResponse(call: Call<Main>, response: Response<Main>) {
            if(response.isSuccessful){
                val res = response.body()
                Log.d("LocationFragment", "Response: $res")
                Toast.makeText(
                    context,
                    "Success",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        override fun onFailure(call: Call<Main>, t: Throwable) {
            Toast.makeText(
                context,
                "Failed to get data: ${t.message}",
                Toast.LENGTH_LONG
            ).show()
            Log.d("LocationFragment", "Failed to get data: ${t.message}")
        }
    }

    override fun onLocationChanged(location: Location) {
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment LocationFragment.
//         */
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//             LocationFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}