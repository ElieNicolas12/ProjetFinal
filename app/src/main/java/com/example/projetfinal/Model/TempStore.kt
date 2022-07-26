package com.example.projetfinal.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.projetfinal.ApiInterface
import com.example.projetfinal.Model.LatLong.coord
import com.example.projetfinal.Model.SubData.Coord
import com.example.projetfinal.ui.home.HomeFragment
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessController.getContext


object TempStore {
    var tempDataList = mutableListOf<TempData>()


    var TempData_Current: TempData? = null

    var Case:Int?=0


    public fun getMyData(coord: LatLng?, context: Context) {


        var res: TempData
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        Log.d("latttt", LatLong.coord?.latitude.toString())
        val retrofitData = retrofitBuilder.getData(coord!!.latitude, coord!!.longitude)

        retrofitData.enqueue(object : Callback<TempData?> {
            override fun onResponse(call: Call<TempData?>, response: Response<TempData?>) {
                val responseBody = response.body()!!
                TempData_Current = TempData(
                    responseBody.base,
                    responseBody.clouds,
                    responseBody.cod,
                    responseBody.coord,
                    responseBody.dt,
                    responseBody.id,
                    responseBody.main,
                    responseBody.name,
                    responseBody.sys,
                    responseBody.timezone,
                    responseBody.visibility,
                    responseBody.weather,
                    responseBody.wind,
                )
                var manager = LocalBroadcastManager.getInstance(context)
                val intent = Intent("com.action.test")
                intent.putExtra("key", "123")
                manager.sendBroadcast(intent);
                Log.d("res", TempData_Current.toString())
            }

            override fun onFailure(call: Call<TempData?>, t: Throwable) {
                Log.d("error0", "ff")
            }
        })
    }

    public fun getMyDatabyCity(cityName:String, context: Context) {


        var res: TempData
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getDatabyCityName(cityName)

        retrofitData.enqueue(object : Callback<TempData?> {
            override fun onResponse(call: Call<TempData?>, response: Response<TempData?>) {
                val responseBody = response.body()!!
                TempData_Current = TempData(
                    responseBody.base,
                    responseBody.clouds,
                    responseBody.cod,
                    responseBody.coord,
                    responseBody.dt,
                    responseBody.id,
                    responseBody.main,
                    responseBody.name,
                    responseBody.sys,
                    responseBody.timezone,
                    responseBody.visibility,
                    responseBody.weather,
                    responseBody.wind,
                )
                var manager = LocalBroadcastManager.getInstance(context)
                val intent = Intent("com.action.test")
                intent.putExtra("key", "123")
                manager.sendBroadcast(intent);
                Log.d("res", TempData_Current.toString())
            }

            override fun onFailure(call: Call<TempData?>, t: Throwable) {
                Log.d("error0", "ff")
            }
        })
    }



}



