package com.example.projetfinal

import android.content.Context
import android.text.TextUtils
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MapWindowAdapter(val context:Context):GoogleMap.InfoWindowAdapter {

    val mWindow=LayoutInflater.from(context).inflate(R.layout.maps_info_window,null)

    private fun setInfoWindow(marker: Marker)
    {
        var title=marker.title
        var tvTitle=mWindow.findViewById<TextView>(R.id.desc)
        if(!TextUtils.isEmpty(title))
        {
            tvTitle.text=title
        }
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