package com.example.projetfinal.Model

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.PERSISTENT_ACTIVITY
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.util.jar.Manifest

object LatLong {
    var coord:LatLng?=null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var city:String?=null


    private  const val Permission_Request_Access_Location=100

    public fun getCurrentLocation(context: Context){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if(checkPermission(context))
        {
            if(isLoactionEnabled(context))
            {

                fusedLocationClient.lastLocation.addOnCompleteListener({
                        task-> val location: Location?=task.result
                    if(location==null)
                    {
                        Toast.makeText(context,"null Recieved", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(context,"Done", Toast.LENGTH_SHORT).show()
                        Log.d("coord",location.toString())
                        coord= LatLng(location.latitude,location.longitude)
                        Log.d("coord2",coord.toString())
                        var manager = LocalBroadcastManager.getInstance(context)
                        val intent = Intent("com.action.test2")
                        intent.putExtra("key2", "1234")
                        manager.sendBroadcast(intent);

                    }
                })
            }
            else
            {
                Toast.makeText(context,"turn on Location", Toast.LENGTH_SHORT).show()
                val intent= Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(context,intent,null)
            }
        }
        else{

            requestPermission(context)
        }

    }

    private fun requestPermission(context: Context) {
        ActivityCompat.requestPermissions(
            context as Activity, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION), Permission_Request_Access_Location
        )
    }

    private fun isLoactionEnabled(context: Context): Boolean {
        val locationManager= context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private  fun checkPermission(context: Context):Boolean
    {
        if(ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            return true
        }
        return false

    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        context: Context
    ) {

        if (requestCode== Permission_Request_Access_Location)
        {
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(context,"Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation(context)
            }
            else
            {
                Toast.makeText(context,"Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}