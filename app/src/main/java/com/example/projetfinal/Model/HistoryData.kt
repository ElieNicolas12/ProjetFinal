package com.example.projetfinal.Model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import android.widget.ImageView
import com.squareup.picasso.RequestCreator
import java.time.LocalDateTime
import java.util.*

data class HistoryData(
    val comment:String,
    val Weatherdata: TempData,
    val imageView: RequestCreator,
    val date: LocalDateTime
)

