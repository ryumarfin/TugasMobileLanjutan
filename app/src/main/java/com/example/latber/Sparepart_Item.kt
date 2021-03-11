package com.example.latihanbersama

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class Sparepart_Item (
    var imgs:Int,
    var detail : String,
    var price : Int
    ): Parcelable