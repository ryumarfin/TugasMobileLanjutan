package com.example.latber.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.latber.R
import kotlinx.android.synthetic.main.activity_beli.*


lateinit var imageView: ImageView
lateinit var button: Button
private val pickImage = 100
private var imageUri: Uri? = null
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PanggilbensinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PanggilbensinFragment : Fragment() {
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

        var objView = inflater.inflate(R.layout.fragment_panggilbensin, container, false)

        imageView = objView.findViewById<ImageView>(R.id.gambar)
        button = objView.findViewById<Button>(R.id.addimage)
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


        /* var btn = objView.findViewById<Button>(R.id.btn_bayarbensin)
         btn.setOnClickListener {
             var intentBaru = Intent(activity, metode::class.java)
             startActivity(intentBaru)
         }

         var et = objView.findViewById<EditText>(R.id.jumlahbensin)
         var btnminus = objView.findViewById<ImageButton>(R.id.btn_minbensin)
         btnminus.setOnClickListener {
             var jlh = et.text.toString().toInt()
             if (jlh<=1)
             else{
                 --jlh
                 et.setText(jlh.toString())
             }
         }
         var btntmbh = objView.findViewById<ImageButton>(R.id.btn_plusbensin)
         btntmbh.setOnClickListener {
             var jlh = et.text.toString().toInt()
             ++jlh
             et.setText(jlh.toString())
         }*/
        return objView
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PanggilbensinFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PanggilbensinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}