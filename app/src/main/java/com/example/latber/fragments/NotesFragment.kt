package com.example.latber.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
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
        var btnBersihkan = objView.findViewById<Button>(R.id.note_bersihkan)
        var btnHapus = objView.findViewById<Button>(R.id.note_hapus)
        var btnBuka = objView.findViewById<Button>(R.id.note_buka)
        var spinner = objView.findViewById<Spinner>(R.id.note_spinner)

        //kirimkan context yg menandakan bahwa database diibentuk dari fragment ini
        mydbHelper = DBHelper(requireContext())

        //refresh tampilan spinner
        onUpgradeAdapter()

        //menyimpan data ke dalam database
        btnSimpan.setOnClickListener{
            var judul = note_judul.text.toString()
            var isi = note_isi.text.toString()

            //cek apakah judul pada edittext = item yg dipilih pada spinner
            if(judul != spinner.selectedItem.toString()){
                //jika ya maka kita lanjutkan p
                //buat variable utk menampung data baru
                val noteTemp = Notes()
                //cek apakah edittext judul kosong tau tidak
                if(judul.trim() != ""){
                    //jka edittext judul tidak kosong, maka simpan data
                    noteTemp.judul = note_judul.text.toString()
                    noteTemp.konten = note_isi.text.toString()

                    //kirimkan data yang akan diinsert
                    var result = mydbHelper?.tambahNote(noteTemp)
                    //cek apakah proses insert berhasil dilakukan atau tidak
                    //jika proses gagal, insert akan mengembalikan nilai -1L
                    if(result !=- 1L){
                        Toast.makeText(context, "Note berhail di simpan", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "Note gagal di simpan", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                    Toast.makeText(context, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            //jika judul pada edittext == item yg dipilih pada spinner, maka kita akan melakukan perubahan
            //data pada database
            else{
                //mengubah atau mengupdate isi konten notes berdasarkan judul
                mydbHelper?.ubahKonten(judul, isi)
            }

            onUpgradeAdapter()
            bersihkan()
        }

        //untuk membaca judul dan konten dari notes
        btnBuka.setOnClickListener{
            doAsync {
                //baca seluruh data dari database
                var listNote = mydbHelper?.tampilkanData()
                //tampung data konten yang dipilih
                var isi = listNote!![spinner.selectedItemId.toInt()].konten
                uiThread {
                    //kemudian tampilkan hasilnya pada edittext
                    note_judul.setText(spinner.selectedItem.toString())
                    note_isi.setText(isi)
                }
            }
//            spinner.onItemSelectedListener = object : OnItemSelectedListener {
//                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
//                    doAsync {
//                        var listNote = mydbHelper?.tampilkanData()
//                        var isi = listNote!![position].konten
//                        uiThread {
//                            note_judul.setText(spinner.selectedItem.toString())
//                            note_isi.setText(isi)
//                        }
//                    }
//                    Toast.makeText(context, id.toString(),Toast.LENGTH_SHORT).show()
//                }
//                override fun onNothingSelected(parentView: AdapterView<*>?) {
//                    Toast.makeText(context, "nothinggggggggg",Toast.LENGTH_SHORT).show()
//                }
//            }
        }

        btnHapus.setOnClickListener{
            //tampung data judul yang diplih melalui spinner untuk dihapus dari database
            var selected = spinner.selectedItem.toString()
            //kemudian lakukan pengecekan apakah data yang dipilih kosong atau tidak
            if(selected.trim() != ""){
                doAsync {
                    //kirimkan data yang ingin dihapus dengan memanggil perintah hapusNote
                    mydbHelper?.hapusNote(selected)
                    //refresh tampilan spinner setelah menghapus Note
                    onUpgradeAdapter()
                }
            }
        }

        //bersihkan/kosongkan semua edittext
        btnBersihkan.setOnClickListener{
            bersihkan()
        }

        return objView
    }

    //functioin untuk me-refresh tampilan spinner dgn membaca data dari databasee
    private fun onUpgradeAdapter() {
        //karena memerlukan waktu, maka proses akan dilakukan secara async
        doAsync {
            //membaca semua data Judul dari database lalu ubah ke dalam bentuk array
            var listJudul = mydbHelper?.tampilkanJudul()?.toTypedArray()

            uiThread {
                //cek apakah spinner ada dan listJudul tidak kosong
                if(note_spinner != null && listJudul?.size != null){
                    //masukkan listJudul ke dalam arrayAdapter untuk ditampilkan dalam spinner
                    var arrayAdapter = ArrayAdapter(requireContext(),
                            android.R.layout.simple_spinner_dropdown_item, listJudul!!)
                    note_spinner.adapter=arrayAdapter
                }
            }
        }
    }

    //membersihkan semua edittext
    private fun bersihkan() {
        note_judul.text.clear()
        note_isi.text.clear()
    }
}