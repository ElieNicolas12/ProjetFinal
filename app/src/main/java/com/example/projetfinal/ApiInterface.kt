package com.example.projetfinal

import com.example.projetfinal.Model.TempData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("?units=metric&appid=29f67eecb170dfbfbf6d2dc637ff448f")
    fun getData(@Query("lat") lat: Double,@Query("lon") lon:Double): Call<TempData>

    @GET("?units=metric&appid=29f67eecb170dfbfbf6d2dc637ff448f")
    fun getDatabyCityName(@Query("q") city:String): Call<TempData>
}

//@GET("?q=beirut&units=metric&appid=29f67eecb170dfbfbf6d2dc637ff448f")
//fun getData(): Call<TempData>