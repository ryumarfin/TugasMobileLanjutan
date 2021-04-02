package com.example.latber.fragments

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import com.example.latber.R
import com.example.latber.REQUEST_CAMERA
import com.example.latber.REQUEST_TAKEPICTURE
import com.example.latber.RESULT_CAMERA


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

// variabel allow untuk menentukan apakah diperbolehkan untuk membuka camera
var allow = true
private val MyBatteryReceiverr = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // cek apakah status batterynya low (bawah 15%) atau berstatus okay
        if (intent.action.equals(Intent.ACTION_BATTERY_LOW))
            allow = false           //jika battery berstatus low, maka tidak diberikan izin (allow = false)
        else
            allow = true            //jika battery berstatus okay, maka  diberikan izin (allow = true)
    }
}



//inisialisasi batteryPercent
var batteryPercent : Int = 0
private val PercentBatteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(ctxt: Context?, intent: Intent) {
        // mengambil data battery level dari intent status battery
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        // mengambil data battery scale dari intent status battery
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        // menghitung persen battery
        batteryPercent = (level * 100 / scale.toFloat()).toInt()
    }
}




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

        var batteryStatus = IntentFilter()
        //tambahkan action yang ingin di handle
        //utk kasus ini, kami menghandle status batteryLow & batterOkay
        batteryStatus.addAction(Intent.ACTION_BATTERY_LOW)
        batteryStatus.addAction(Intent.ACTION_BATTERY_OKAY)

        //mendaftarkan receiver di dlm PostingFragment agar PostingFragment dapat menangkap broadcast
        activity?.registerReceiver(MyBatteryReceiverr, batteryStatus)


        var batteryPercentFilter = IntentFilter()
        //tambahkan action untuk menghandle perubahan pada battery
        batteryPercentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        //daftarkan receiver agar Posting Fragment dapat menangkap broadcast
        activity?.registerReceiver(PercentBatteryReceiver, batteryPercentFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        //unregister receiver
        activity?.unregisterReceiver(MyBatteryReceiverr)
        activity?.unregisterReceiver(PercentBatteryReceiver)
    }







    //inisialisasi InterfaceData
    private lateinit var interfaceData: InterfaceData

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //agar dapat memanggil atau menggunakan property dari layout fragment
        var objView = inflater.inflate(R.layout.fragment_posting, container, false)

        imageView = objView.findViewById<ImageView>(R.id.gambar)
        button = objView.findViewById<Button>(R.id.addimage)
        button.setOnClickListener {

//            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            startActivityForResult(gallery, pickImage)

            var dialogBuilder = AlertDialog.Builder(activity!!)
                    .setTitle("Add Image")
                    .setPositiveButton("CAMERA", DialogInterface.OnClickListener { dialog, id ->
                        //cek apakah diberikan izin untuk membuka kamera (allow = true)
                        if (allow == false)     // jika allow = false (battery berstatus low)
                            Toast.makeText(context, "Please Charge Your Phone", Toast.LENGTH_SHORT).show()
                        else {      // jika allow = true (battery berstatus okay)
                            //cek apakah user sudah memberikan akses kepada aplikasi untuk membuka camera
                            if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                //jika belum diberikan akses, maka lakukan request/minta izin untuk mengakses camera
                                requestPermissions(activity!!, arrayOf(android.Manifest.permission.CAMERA), REQUEST_CAMERA)
                            }
                            else {
                                //jika user telah memberikan akses camera
                                var takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                if (takePicture.resolveActivity(activity!!.getPackageManager()) != null)
                                    startActivityForResult(takePicture, REQUEST_TAKEPICTURE)
                            }
                        }
                    })
                    .setNegativeButton("GALERY", DialogInterface.OnClickListener { dialog, id ->
                        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                        startActivityForResult(gallery, pickImage)
                    })
                    .setNeutralButton("Cek % Battery", DialogInterface.OnClickListener{ dialog, id ->
                        //munculkan Toast yang berisi persentase battery saat ini
                        Toast.makeText(context, batteryPercent.toString(), Toast.LENGTH_SHORT).show()
                    })
                val alert = dialogBuilder.create()
                alert.show()
        }

        //menambahkan interfacedata dengan aktivitas dari InterfaceData
        interfaceData = activity as InterfaceData
        //inisialisasi komponen dari fragment
        val btnPost = objView.findViewById<Button>(R.id.btn_post)
        val detailBarang = objView.findViewById<EditText>(R.id.detailBarang)
        val detailHarga = objView.findViewById<EditText>(R.id.hargaBarang)

        btnPost.setOnClickListener{
            //pengiriman data oleh property KirimData
            interfaceData.KirimData(detailBarang.text.toString(), detailHarga.text.toString())
            detailBarang.setText("")
            detailHarga.setText("")
        }


        return objView
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
        else if(resultCode == RESULT_OK && requestCode == REQUEST_TAKEPICTURE){
            var tumbnail = data?.extras?.get("data")
            imageView.setImageBitmap(tumbnail as Bitmap)
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

