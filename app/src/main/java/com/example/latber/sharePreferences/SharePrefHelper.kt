package com.example.latber.sharePreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.latber.activities.KEY_EMAIL
import com.example.latber.activities.KEY_PASS

//tambah parameter context & fileName
class SharePrefHelper(context: Context, fileName: String){
    private var myPreferences : SharedPreferences
    //inisialisasi myPreferences
    init {
        myPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        //plih yg private yg artinya hanya aplikasi ini yg dpt mengakses datanya
    }

    //  menambahkan fungsi .editMe ke dlm SharedPreferences
    //  yg memilik parameter sebuah fungsi yaitu "opertion" yg akan mengembalikan Unit
    inline fun SharedPreferences.editMe(opertion:(SharedPreferences.Editor)->Unit){
        // buat var utk menampung fungsi edit() dri SharePreferences
        val editMe = edit()
        // kirimkan editMe
        opertion(editMe)    //isiny dpt berupa putInt, putString, putBolean dll
        // jalankan
        editMe.apply()
    }
    //perintah dlm inline bersifat duplikasi sehingga dpt lbh menghemat penggunaan memory
    var email : String?
        //Accessor
        get() = myPreferences.getString(KEY_EMAIL, "")

        //mutator
        set(value) {
            myPreferences.editMe {
                it.putString(KEY_EMAIL, value)
            }
        }
    var pass : String?
        get() = myPreferences.getString(KEY_PASS, "")
        set(value) {
            myPreferences.editMe {
                it.putString(KEY_PASS, value)
            }
        }

//    fun clearValues() {
//        myPreferences.editMe {
//            it.clear()
//        }
//    }

    //1.10 ke MainActivity


    //tidak akan digunakan krna tdk menghemat memory
//    //1.6 buat var utk menyimpan data ke dlm sharePreference nntinya
//    //cara biasa -> cara ini mengulang2 memanggil edit() dan apply()
//    // cara lbh baik, dgn menggunakan cara di atas (buat inline fun) -> 1.8
//    var nama : String?
//        //buat Accessor
//        // klo tdk ditemukan maka "Kosong"
//        get() = myPreferences.getString(USER_NAME,"Kosong")
//
//        //buat mutator
//        set(value) {
//            //utk mengisi data ke dlm SharePreferences gunakan .edit()
//            //utk mejalankan perintah (.edit()) gunakan .apply()
//            myPreferences.edit().putString(USER_NAME,value).apply()
//        }
//    var email : String?
//        get() = myPreferences.getString(USER_EMAIL,"Kosong")
//        set(value) {
//            myPreferences.edit().putString(USER_EMAIL, value).apply()
//        }
//
//    //1.7 utk membersihkan datanya
//    fun clearValues(){
//        myPreferences.edit().clear().apply()
//    }
}