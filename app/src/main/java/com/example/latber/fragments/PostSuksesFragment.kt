package com.example.latber.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.latber.R
import com.example.latber.activities.dapatMontir

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PanggilmontirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class PanggilmontirFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //agar dapat memanggil atau menggunakan property dari layout fragment
        var objView = inflater.inflate(R.layout.fragment_postsucces, container, false)

        //inisialisasi detailBarang & detailHarga dengan data sesuai dengan key
        var detailBarang = arguments?.getString("dataDetailBarang")
        var detailHarga = arguments?.getString("dataHarga")

        //inisialisasi komponen dari fragment
        val hasil = objView.findViewById<TextView>(R.id.detailBarangPost)
        val harga = objView.findViewById<TextView>(R.id.hargaPost)

        //mengatur Text atau menampilkan data yang dikirim dari PostingFragment.kt
        hasil.setText(detailBarang)
        harga.setText(detailHarga)

        return objView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PanggilmontirFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PanggilmontirFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}