package com.example.projetfinal

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projetfinal.Model.HistoryData
import com.example.projetfinal.Model.SubData.*
import com.example.projetfinal.Model.TempData
import com.example.projetfinal.Model.TempStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.nio.file.Files.delete
import java.sql.Time
import java.time.LocalDateTime

class itemviewAdapter(val c:Context,val Templist:ArrayList<HistoryData>):RecyclerView.Adapter<itemviewAdapter.ViewHolder>() {



    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var itemImage:ImageView
        var itemDate:TextView
        var itemComment:TextView

        init {
            itemImage=itemView.findViewById(R.id.itemImageIV)
            itemDate=itemView.findViewById(R.id.itemDateTV)
            itemComment=itemView.findViewById(R.id.itemCommentTV)
        }
    }


    override fun getItemCount(): Int {
       return Templist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewHolder((v))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newlist=Templist[position]
        holder.itemDate.text= LocalDateTime.now().toString()
        holder.itemImage.setImageBitmap(newlist.imageView!!)
        holder.itemComment.text=newlist.comment

    }



}

//    public fun getApi(context: Context)
//    {
//        val url =  "https://62cdd1f8a43bf780085f672f.mockapi.io/weatherapp/weatherapp"
//        val queue = Volley.newRequestQueue(context)
//        val postRequest = JsonArrayRequest(
//            Request.Method.GET, url, null,
//            {
//                    response -> Log.d("Resp1", response.toString())
//                for (i in 0 until response.length())
//                {
//                    val jsonObject = response.getJSONObject(i)
//                    Log.d("reso", jsonObject.toString())
//                    val name=jsonObject.getString("name")
//                    val base=jsonObject.getString("base")
//                    val clouds: Clouds = Clouds(jsonObject.getInt("clouds"))
//                    var cod=jsonObject.getInt("cod")
//                    var coord_A=jsonObject.getJSONArray("coord")
//                    var Coord: Coord = Coord(coord_A[0] as Double,coord_A[1] as Double)
//                    val dt=jsonObject.getInt("dt")
//                    var id=jsonObject.getInt("id")
//                    var _sys=jsonObject.getJSONArray("sys")
//                    var sys= Sys(_sys[0] , _sys[1] as Int,
//                        _sys[2]!!, _sys[3] as Int, _sys[4] as Int, _sys[5] as Int
//                    )
//                    var _main=jsonObject.getJSONArray("main")
//                    var main= Main(_main[0] as Double,
//                        _main[1] as Int, _main[2] as Int, _main[3] as Double,
//                        _main[4] as Double, _main[5] as Double
//                    )
//                    var timezone=jsonObject.getInt("timezone")
//                    var visibility=jsonObject.getInt("visibility")
//                    var weather=jsonObject.getJSONArray("weather")
//                    var _wind=jsonObject.getJSONArray("wind")
//                    var wind= Wind(_wind[0] as Int, _wind[1] as Double)
//
//                    val res: TempData = TempData(base,clouds,cod,Coord,dt,id,main,name,
//                        sys,timezone,null,null,wind)
//
//                    TempStore.tempDataList.add(i,res)
//                    var manager = LocalBroadcastManager.getInstance(context)
//                    val intent = Intent("com.action.test2")
//                    intent.putExtra("key", "123")
//                    manager.sendBroadcast(intent);
//                    Log.d("list", TempStore.tempDataList[i].toString())
//
//                }
//            },
//            {
//                    error -> Log.d("Error", error.toString())
//            })
//        queue.add(postRequest)
//    }

//    public fun PostAPI(Dice: TempStore,context: Context)
//    {
//        val url = "https://62cdd1f8a43bf780085f672f.mockapi.io/weatherapp/weatherapp"
//        val queue = Volley.newRequestQueue(context)
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.POST, url, JSONObject(
//                "{" +
//                        "\"name\":${Dice.TempData_Current?.name}," +
//                        "\"base\":${Dice.TempData_Current?.base},"+
//                        "\"clouds\":${Dice.TempData_Current?.clouds?.all},"+
//                        "\"cod\":${Dice.TempData_Current?.cod},"+
//                        "\"coord\":[${Dice.TempData_Current?.coord?.lon},${Dice.TempData_Current?.coord?.lat}],"+
//                        "\"dt\":${Dice.TempData_Current?.dt},"+
//                        "\"id\":${Dice.TempData_Current?.id},"+
//                        "\"main\":[${Dice.TempData_Current?.main!!.feels_like},${Dice.TempData_Current?.main!!.humidity}," +
//                        "${Dice.TempData_Current?.main!!.pressure},${Dice.TempData_Current?.main!!.temp}," +
//                        "${Dice.TempData_Current?.main!!.temp_max},${Dice.TempData_Current?.main!!.temp_min}],"+
//                        "\"sys\":[${Dice.TempData_Current?.sys?.country},${Dice.TempData_Current?.sys?.id}," +
//                        "${Dice.TempData_Current?.sys?.message},${Dice.TempData_Current?.sys?.sunrise}," +
//                        "${Dice.TempData_Current?.sys?.sunset},${Dice.TempData_Current?.sys?.type}],"+
//                        "\"timezone\":${Dice.TempData_Current?.timezone},"+
//                        "\"visibility\":${Dice.TempData_Current?.visibility},"+
//                        "\"wind\":[${Dice.TempData_Current?.wind?.deg},${Dice.TempData_Current?.wind?.speed}]"+
//
//                        "}"
//
//            ),
//            { response -> println(response) },
//            { error -> error.printStackTrace() })
//        queue.add(jsonObjectRequest)
//
//    }





