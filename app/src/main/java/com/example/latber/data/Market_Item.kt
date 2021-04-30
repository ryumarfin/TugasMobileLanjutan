package com.example.latber.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//buat class menjadi parcelable agar secara otomatis data akan dikirim dalam bentuk parcelable
@Parcelize
//model MVP
 data class Market_Item (
    var imgs:Int,
    var detail : String,
    var price : Int
    ): Parcelable