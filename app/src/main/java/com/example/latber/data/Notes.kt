package com.example.latber.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//buat class parcelable
@Parcelize
data class Notes(
    var id : Int = 0,
    var judul : String = "",
    var konten : String = ""):Parcelable{
}