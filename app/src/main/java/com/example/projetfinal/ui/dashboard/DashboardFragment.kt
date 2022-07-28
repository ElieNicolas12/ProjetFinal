package com.example.projetfinal.ui.dashboard

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetfinal.Model.HistoryData
import com.example.projetfinal.Model.TempStore
import com.example.projetfinal.R
import com.example.projetfinal.databinding.FragmentDashboardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class DashboardFragment : Fragment() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var Templist: ArrayList<HistoryData>
    private lateinit var adapter: itemviewAdapter

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!


//    private  var layoutManager:RecyclerView.LayoutManager?=null

    lateinit var photo: Bitmap

    companion object {
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Templist = ArrayList()
        /**set find Id*/
        addsBtn = binding.addingBtn
        recv = binding.weatherList
        /**set Adapter*/
        adapter = itemviewAdapter(this.context!!, Templist)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this.context)
        recv.adapter = adapter
        addsBtn.setOnClickListener { addInfo() }

           return root
    }


    private fun addInfo() {
        val inflter = LayoutInflater.from(this.context)
        val v = inflter.inflate(R.layout.add_item,null)
    val imageView_Iv=v.findViewById<ImageView>(R.id.camera)
    val comment_TV = v.findViewById<EditText>(R.id.comments)
    val btnCamera=v.findViewById<Button>(R.id.btnCamera)
    val addDialog = AlertDialog.Builder(this.context)
        addDialog.setView(v)
    if (ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_DENIED)
        ActivityCompat.requestPermissions(this.context as Activity, arrayOf(Manifest.permission.CAMERA), 1888)
    btnCamera.setOnClickListener {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1888)
    }

    addDialog.setPositiveButton("Ok"){
            dialog,_->
        val icon= Picasso.get()
            .load("https://openweathermap.org/img/w/${TempStore.TempData_Current?.weather?.get(0)?.icon}.png")


        val comment = comment_TV.text.toString()
        Templist.add(HistoryData(comment,TempStore.TempData_Current!!,icon,LocalDateTime.now()))
        adapter.notifyDataSetChanged()
        Toast.makeText(this.context,"Adding User Information Success",Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
    addDialog.setNegativeButton("Cancel"){
            dialog,_->
        dialog.dismiss()
        Toast.makeText(this.context,"Cancel",Toast.LENGTH_SHORT).show()

    }
    addDialog.create()
    addDialog.show()
}


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1888) {
//            val imageView=v.findViewById<ImageView>(R.id.camera)
//            photo= data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(photo)
//        }
//    }
//


}











