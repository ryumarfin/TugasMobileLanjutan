package com.example.latber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanbersama.Market_Item


class SparepartListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sparepart_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sparePartGridView = view.findViewById<GridView>(R.id.myGridView)


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


        val adapter = Market_Items_Adapter(requireContext(),arrayList)

//        sparePartGridView. = LinearLayoutManager(requireContext())
        sparePartGridView.adapter = adapter
    }
}