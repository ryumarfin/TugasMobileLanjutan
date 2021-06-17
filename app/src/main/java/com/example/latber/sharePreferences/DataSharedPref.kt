package com.example.latber.sharePreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.latber.activities.*

//berikut shared preferences untuk data coin dan member user
//tambah parameter context & fileName
class DataSharedPref(context: Context, fileName: String) {
    private var myPreferences: SharedPreferences

    //inisialisasi myPreferences
    init {
        myPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        //plih yg private yg artinya hanya aplikasi ini yg dpt mengakses datanya
    }

    //  menambahkan fungsi .editMe ke dlm SharedPreferences
    //  yg memilik parameter sebuah fungsi yaitu "opertion" yg akan mengembalikan Unit
    inline fun SharedPreferences.editMe(opertion: (SharedPreferences.Editor) -> Unit) {
        // buat var utk menampung fungsi edit() dri SharePreferences
        val editMe = edit()
        // kirimkan editMe
        opertion(editMe)    //isiny dpt berupa putInt, putString, putBolean dll
        // jalankan
        editMe.apply()
    }

    //Rewardcoin untuk menyimpan jumlah coin yang dikumpulkan user
    var Rewardcoin: Int?
        get() = myPreferences.getInt(KEY_COIN_REWARDS, 0)
        set(value){
            myPreferences.editMe {
                it.putInt(KEY_COIN_REWARDS, value!!.toInt())
            }
        }
    //VIP_member untuk menyimpan data member user
    var VIP_member: Boolean?
        get() = myPreferences.getBoolean(KEY_MEMBER, false)
        set(value){
            myPreferences.editMe {
                it.putBoolean(KEY_MEMBER, value!!)
            }
        }
}