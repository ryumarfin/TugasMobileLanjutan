package com.example.latber.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.latber.R
import com.example.latber.data.Notes
import com.example.latber.sql.DBHelper
import kotlinx.android.synthetic.main.fragment_notes.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    //inisialisasi DBHelper
    var mydbHelper : DBHelper ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var objView = inflater.inflate(R.layout.fragment_notes, container, false)

        var btnSimpan = objView.findViewById<Button>(R.id.note_simpan)

        //kirimkan context yg menandakan bahwa database diibentuk dari fragment ini
        mydbHelper = DBHelper(requireContext())

        btnSimpan.setOnClickListener{
            //buat variable utk menampung data baru
            val noteTemp = Notes()

            //cek apakah edittext judul kosong tau tidak
            if(note_judul.text.toString().trim() != ""){
                //jka edittext judul tidak kosong, maka simpan data
                noteTemp.judul = note_judul.text.toString()
                noteTemp.konten = note_isi.text.toString()

                var result = mydbHelper?.tambahNote(noteTemp)

                if(result !=- 1L){
                    Toast.makeText(context, "Note berhail di simpan", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Note gagal di simpan", Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(context, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show()

            onUpgradeAdapter()
            note_judul.text.clear()
            note_isi.text.clear()
        }

        return objView
    }

    private fun onUpgradeAdapter() {
        doAsync {
            var listJudul = mydbHelper?.tampilkanData()?.toTypedArray()

            uiThread {
                if(note_spinner != null && listJudul?.size != null){
                    var arrayAdapter = ArrayAdapter(requireContext(),
                            android.R.layout.simple_spinner_dropdown_item, listJudul!!)
                    note_spinner.adapter=arrayAdapter
                }
            }
        }
    }
}