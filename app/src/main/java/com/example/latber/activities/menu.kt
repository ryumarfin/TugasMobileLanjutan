package com.example.latber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.latber.R
import com.example.latber.fragments.*
import kotlinx.android.synthetic.main.activity_menu.*

class menu : AppCompatActivity() {

    private val sparepartFragment = SparepartListFragment()
    private val panggilbensinFragment = PanggilbensinFragment()
    private val panggilmontirFragment = PanggilmontirFragment()
    private val historyFragment = HistoryFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        replaceFragment(sparepartFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_market -> replaceFragment(sparepartFragment)
                R.id.nav_Jual -> replaceFragment(panggilbensinFragment)
                R.id.nav_panggilmontir -> replaceFragment(panggilmontirFragment)
                R.id.nav_history -> replaceFragment(historyFragment)
                R.id.nav_profile -> replaceFragment(profileFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment)
    {
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}