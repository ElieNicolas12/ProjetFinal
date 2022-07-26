package com.example.projetfinal.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.projetfinal.Model.LatLong
import com.example.projetfinal.Model.SubData.Weather
import com.example.projetfinal.Model.TempStore
import com.example.projetfinal.R
import com.example.projetfinal.databinding.FragmentHomeBinding
import com.google.android.gms.maps.model.LatLng
import java.security.AccessController.getContext
import java.text.SimpleDateFormat


class HomeFragment(): Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var city:String
    private val binding get() = _binding!!

    lateinit var loader:ProgressBar
    lateinit var mainContainer: RelativeLayout
    lateinit var  errorText: TextView
    lateinit var address:TextView
    lateinit var updated_at:TextView
    lateinit var status:TextView
    lateinit var temp:TextView
    lateinit var temp_min:TextView
    lateinit var temp_max:TextView
    lateinit var sunrise:TextView
    lateinit var sunset:TextView
    lateinit var wind:TextView
    lateinit var pressure:TextView
    lateinit var humidity:TextView
    lateinit var about:TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initBroadCastReceiver2()
        LatLong.getCurrentLocation(this.context!!)
        Log.d("les coordonne2",LatLong.coord.toString())
        initBroadCastReceiver();



         loader =binding.loader
            loader.visibility=View.VISIBLE

        mainContainer =binding.mainContainer
            mainContainer.visibility = View.GONE

        errorText=binding.errorText
            errorText.visibility = View.GONE

        address=binding.address

        updated_at=binding.updatedAt
        status=binding.status
        temp=binding.temp
        temp_min=binding.tempMin
        temp_max=binding.tempMax
        sunrise=binding.sunrise
        sunset=binding.sunset
        wind=binding.wind
        pressure=binding.pressure
        humidity=binding.humidity
        about=binding.about




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initBroadCastReceiver2() {
        var manager = LocalBroadcastManager.getInstance(getContext()!!)
        val receiver = MyBroadCastReceiver2(this)
        val filter = IntentFilter()
        filter.addAction("com.action.test2")
        manager.registerReceiver(receiver, filter)
    }
    public fun initBroadCastReceiver() {
        var manager = LocalBroadcastManager.getInstance(getContext()!!)
        val receiver = MyBroadCastReceiver(this)
        val filter = IntentFilter()
        filter.addAction("com.action.test")
        manager.registerReceiver(receiver, filter)
    }

}

internal class MyBroadCastReceiver(val frag:HomeFragment) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val key = intent.getStringExtra("key")
        Log.d("ddddd",TempStore.TempData_Current.toString())

        frag.loader.visibility=View.GONE
        frag.mainContainer.visibility = View.VISIBLE
        frag.address.text= TempStore.TempData_Current?.name.toString()
        val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = simpleDate.format(TempStore.TempData_Current!!.id)
        frag.updated_at.text=currentDate
        frag.status.text= TempStore.TempData_Current!!.weather!![0].description
        frag.temp.text=TempStore.TempData_Current!!.main!!.temp.toString()+"°C"
//        Log.d("ok",sdf.format(frag.id ))
    }
}
internal class MyBroadCastReceiver2(val frag:HomeFragment) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val key = intent.getStringExtra("key2")
        Log.d("les coordonne",LatLong.coord.toString())
        if(TempStore.Case==1)
        {
            TempStore.getMyDatabyCity(LatLong.city.toString(),frag.context!!)

        }
        else
        {
            TempStore.getMyData(LatLng(LatLong.coord!!.latitude, LatLong.coord!!.longitude), frag.context!!);

        }
        }

}






