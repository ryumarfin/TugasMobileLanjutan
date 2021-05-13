package com.example.latber.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.latber.FavoritePhotos
import com.example.latber.Interface.InterfaceData
import com.example.latber.R
import com.example.latber.fragments.*
import kotlinx.android.synthetic.main.activity_menu.*

class menu : AppCompatActivity(), InterfaceData {

    private val sparepartFragment = SparepartFragment()
    private val panggilbensinFragment = PanggilbensinFragment()
    private val postSuksesFragment = PanggilmontirFragment()
    private val historyFragment = HistoryFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_overflow,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite -> {
                val intent = Intent(this, FavoritePhotos::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        replaceFragment(sparepartFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_market -> replaceFragment(sparepartFragment)
                R.id.nav_Post -> replaceFragment(panggilbensinFragment)
                R.id.nav_panggilmontir -> replaceFragment(postSuksesFragment)
                R.id.nav_history -> replaceFragment(historyFragment)
                R.id.nav_profile -> replaceFragment(profileFragment)
            }
            true
        }

// KASJDKAJSNDKJANSDJKANSDKJJAWS
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, panggilbensinFragment).commit()

    }

    private fun replaceFragment(fragment: Fragment)
    {
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

//    //implementasi member InterfaceData untuk pengiriman data antar fragment dengan bundle
//    override fun KirimData(edittext: String) {
//        val bundle = Bundle()
//        bundle.putString("dataDetailBarang", edittext)
//        bundle.putString("dataHarga", edittext)
//
//        val transaksi = this.supportFragmentManager.beginTransaction()
//
//        postSuccessFragment.arguments = bundle
//
//        transaksi.replace(R.id.fragment_container, postSuccessFragment)
//
//        transaksi.addToBackStack(null)
//
//        transaksi.commit()
////        transaksi.hide()
//    }

    //implementasi member InterfaceData untuk pengiriman data antar fragment dengan bundle
    override fun KirimData(edittext: String, edittext2: String) {
        //inisialisasi bundle
        val bundle = Bundle()
        //buat key untuk masing2 data yang akan dikirim
        bundle.putString("dataDetailBarang", edittext)
        bundle.putString("dataHarga", edittext2)

        //untuk memulai transaksi dan kirim bundle ke property arguments dari postSuccessFragment
        val transaksi = this.supportFragmentManager.beginTransaction()
        postSuksesFragment.arguments = bundle

        //menimpa fragment posting dengan postSuccessFragment
        transaksi.replace(R.id.fragment_container, postSuksesFragment)
        //agar tdak langsung keluar karena tidak ada stack di fragment_container
        transaksi.addToBackStack(null)
        //lakukan commit
        transaksi.commit()
    }


}