package com.example.latber.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//buat data class yang akan dikirim dengan parcelable
@Parcelize
data class Quotes(
    val text : String,
    val author : String
) : Parcelable