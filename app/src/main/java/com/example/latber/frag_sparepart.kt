package com.example.latber

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.latihanbersama.Sparepart_Item


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [frag_sparepart.newInstance] factory method to
 * create an instance of this fragment.
 */
class frag_sparepart : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


            arrayList = ArrayList()
            arrayList = setDataList()

            val AGC =  getActivity()?.getApplicationContext()

            sparepartItemsAdapter = Sparepart_Items_Adapter(AGC!!, arrayList!!)
        }
    }

    private lateinit var myadapter :Sparepart_Items_Adapter

    private var arrayList: ArrayList<Sparepart_Item>? = null
    private var sparepartItemsAdapter: Sparepart_Items_Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val activity = frag_sparepart as Context
//        val recyclerView = view.findViewById<GridView>(R.id.myGridView)
//        recyclerView.layoutManager = GridLayoutManager(activity, 2)
//        recyclerView.adapter = DogListAdapter(activity)
//        return view


        var objView = inflater.inflate(R.layout.fragment_frag_sparepart, container, false)


        return objView
    }
    private fun setDataList(): ArrayList<Sparepart_Item> {
        var arrayList: ArrayList<Sparepart_Item> = ArrayList()

        arrayList.add(Sparepart_Item(R.drawable.pic1, "Kunci Kontak Vespa New", 10000))
        arrayList.add(
            Sparepart_Item(
                R.drawable.pic2,
                "Kunci Kontak Vespa New barang bagus sekaliiii",
                20000
            )
        )
        arrayList.add(Sparepart_Item(R.drawable.pic1, "Barang Bagus", 30000))
        arrayList.add(Sparepart_Item(R.drawable.pic2, "Ban Dalam SupraX", 100000))
        arrayList.add(Sparepart_Item(R.drawable.pic4, "Kaca Sepion hk", 150000))
        arrayList.add(Sparepart_Item(R.drawable.pic3, "Tromol 540x", 15000))

        return arrayList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment frag_sparepart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                frag_sparepart().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var sparepartItem: Sparepart_Item = arrayList!!.get(position)

//        var intentItem = Intent(this, beli::class.java)

        /*img1.setDrawingCacheEnabled(true)
        val gambar: Bitmap = img1.getDrawingCache()

        intentItem.putExtra("Bitmap", gambar)*/

//        intentItem.putExtra("resId", sparepartItem.imgs)
//
//        intentItem.putExtra(EXTRA_KETERANGAN, sparepartItem.detail.toString())
//        intentItem.putExtra(EXTRA_HARGA, sparepartItem.price.toString())
//
//        startActivity(intentItem)
    }
}