package com.example.projetfinal.Model

import android.graphics.Bitmap
import android.provider.ContactsContract
import android.widget.ImageView

data class HistoryData(
    val comment:String,
    val Weatherdata: TempData,
    val imageView: Bitmap?
)
