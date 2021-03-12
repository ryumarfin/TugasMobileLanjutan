package com.example.latber.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.latber.R
import com.example.latber.Market_Items_Adapter
import com.example.latihanbersama.Market_Item

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SparepartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SparepartFragment : Fragment() {
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



    private lateinit var myadapter :Market_Items_Adapter

    private var arrayList: ArrayList<Market_Item>? = null
    private var sparepartItemsAdapter: Market_Items_Adapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        val view: View = inflater.inflate(R.layout.fragment_frag_sparepart, container,
//            false)
        val activity = activity as Context
//        val recyclerView = view.findViewById<GridView>(R.id.myGridView)
//        recyclerView.layoutManager = GridLayoutManager(activity, 2)
//        recyclerView.adapter = DogListAdapter(activity)
//        return view


        var objView = inflater.inflate(R.layout.fragment_frag_sparepart, container, false)

        arrayList = ArrayList()
        arrayList = setDataList()

//        var grdview1 = objView.findViewById<GridView>(R.id.myGridView)

        sparepartItemsAdapter = Market_Items_Adapter( activity, arrayList!!)

//        grdview1?.adapter = sparepartItemsAdapter
//        grdview1?.onItemClickListener = this


        /*var btn = objView.findViewById<Button>(R.id.btn_frag1)
        btn.setOnClickListener {
            var intentBaru = Intent(activity, register::class.java)
            startActivity(intentBaru)
        }*/

        return objView







//        return inflater.inflate(R.layout.fragment_frag_sparepart, container, false)
    }

    private fun setDataList(): ArrayList<Market_Item> {
        var arrayList: ArrayList<Market_Item> = ArrayList()

        arrayList.add(Market_Item(R.drawable.pic1, "Kunci Kontak Vespa New", 10000))
        arrayList.add(
            Market_Item(
                R.drawable.pic2,
                "Kunci Kontak Vespa New barang bagus sekaliiii",
                20000
            )
        )
        arrayList.add(Market_Item(R.drawable.pic1, "Barang Bagus", 30000))
        arrayList.add(Market_Item(R.drawable.pic2, "Ban Dalam SupraX", 100000))
        arrayList.add(Market_Item(R.drawable.pic4, "Kaca Sepion hk", 150000))
        arrayList.add(Market_Item(R.drawable.pic3, "Tromol 540x", 15000))

        return arrayList
    }








    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SparepartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SparepartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}