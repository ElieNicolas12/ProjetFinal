package com.example.projetfinal.ui.dashboard

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projetfinal.Model.HistoryData
import com.example.projetfinal.Model.TempStore
import com.example.projetfinal.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

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
        holder.itemDate.text= newlist.date.toString()
        newlist.imageView.into(holder.itemImage)
        holder.itemComment.text=newlist.comment

        holder.itemView.setOnClickListener{

            val inflter = LayoutInflater.from(c)
            val v = inflter.inflate(R.layout.item_detailed,null)
            val item_Detailed_Date_Tv=v.findViewById<TextView>(R.id.item_Detailed_Date)
            val item_Detailed_Location_Tv=v.findViewById<TextView>(R.id.item_Detailed_Location)
            val item_Detailed_Sunrise_Tv=v.findViewById<TextView>(R.id.item_Detailed_Sunrise)
            val item_Detailed_sunset_Tv=v.findViewById<TextView>(R.id.item_Detailed_Sunset)
            val item_Detailed_Temp_Tv=v.findViewById<TextView>(R.id.item_Detailed_Temp)
            val item_Detailed_Comment_Tv=v.findViewById<TextView>(R.id.item_Detailed_Comment)
            val item_Detailed_Icon_Iv=v.findViewById<ImageView>(R.id.item_Detailed_Icon)

            val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

            item_Detailed_Date_Tv.text= newlist.date.toString()
            item_Detailed_Location_Tv.text=newlist.Weatherdata.name.toString()+", "+newlist.Weatherdata.sys?.country.toString()
            item_Detailed_Sunrise_Tv.text=simpleDate.format(newlist.Weatherdata.sys?.sunrise).toString()
            item_Detailed_sunset_Tv.text=simpleDate.format(newlist.Weatherdata.sys?.sunset).toString()
            item_Detailed_Temp_Tv.text=newlist.Weatherdata.main?.temp.toString()
            item_Detailed_Comment_Tv.text=newlist.comment
            newlist.imageView.into(item_Detailed_Icon_Iv)
            val addDialog = AlertDialog.Builder(c)
            addDialog.setView(v)

            addDialog.setPositiveButton("Ok"){
                    dialog,_->
                dialog.dismiss()
            }
            addDialog.create()
            addDialog.show()
        }

    }


}






