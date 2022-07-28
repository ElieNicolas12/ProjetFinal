package com.example.projetfinal.ui.MapFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetfinal.Model.LatLong
import com.example.projetfinal.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        LatLong.getCurrentLocation(this.context!!)
        mapFragment?.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {

        val country = LatLong.coord
        val zoomLevel = 16.0f
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(country!!, zoomLevel))
        googleMap.addMarker(
            MarkerOptions()
                .position(country))
        googleMap.setInfoWindowAdapter(MapWindowAdapter(this.context!!))
    }


}