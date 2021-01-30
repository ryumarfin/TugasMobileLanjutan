package com.example.latber

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_panggilmontir.*


class activity_panggilmontir : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panggilmontir)

        btn_cariMontir.setOnClickListener {
            // Initialize a new layout inflater instance
            val inflater: LayoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.activity_popup_dapatmontir, null)

            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // If API level 23 or higher then execute the code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.BOTTOM
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }
            // Get the widgets reference from custom view

            val btn_panggilmontir = view.findViewById<Button>(R.id.btn_panggilmontir)
            val btn_cariMontirlagi = view.findViewById<Button>(R.id.btn_cariMontirLagi)
            val btn_batal = view.findViewById<Button>(R.id.btn_Batalpanggil)


            btn_batal.setOnClickListener {
                // Dismiss the popup window
                popupWindow.dismiss()
            }

            popupWindow.setOnDismissListener {
                Toast.makeText(applicationContext, "Dibatalkan", Toast.LENGTH_SHORT).show()
            }

            TransitionManager.beginDelayedTransition(rootpanggilmontir)
            popupWindow.showAtLocation(
                rootpanggilmontir, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                300 // Y offset
            )


        }


    }

    fun tocarimontir(view: View) {}
}