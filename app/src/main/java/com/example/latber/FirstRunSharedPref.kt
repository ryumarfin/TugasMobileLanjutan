package com.example.latber

import android.content.Context

class FirstRunSharedPref(context: Context) {
    private val keyPref = "FIRST_RUN"
    private val mySharedPref = context.getSharedPreferences(
            "SharedPrefFile", Context.MODE_PRIVATE
    )
    var firstRun : Boolean
    get() = mySharedPref.getBoolean(keyPref,true)
    set(value) {mySharedPref.edit().putBoolean(keyPref,value).commit()}
}