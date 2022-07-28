package com.example.projetfinal.ui.MapFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.projetfinal.Model.TempStore
import com.example.projetfinal.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MapWindowAdapter(val context:Context):GoogleMap.InfoWindowAdapter {

    val mWindow=LayoutInflater.from(context).inflate(R.layout.maps_info_window,null)

    private fun setInfoWindow(marker: Marker)
    {
        val CountryName_TV =mWindow.findViewById<TextView>(R.id.CountryName)
        val CountryWeatherDesciption_TV=mWindow.findViewById<TextView>(R.id.CountryWeatherDetails)

        CountryName_TV.text=TempStore.TempData_Current!!.name.toString()
        CountryWeatherDesciption_TV.text="Country: "+TempStore.TempData_Current!!.sys!!.country+
                "\nLat: "+TempStore.TempData_Current!!.coord!!.lat.toString()+
                "\nLong: "+TempStore.TempData_Current!!.coord!!.lon.toString()+
                "\nTemp: "+TempStore.TempData_Current!!.main!!.temp.toString()


    }
    override fun getInfoContents(p0: Marker): View? {
        setInfoWindow(marker = p0)
        return mWindow
    }

    override fun getInfoWindow(p0: Marker): View? {
        setInfoWindow(marker = p0)
        return mWindow
    }

}