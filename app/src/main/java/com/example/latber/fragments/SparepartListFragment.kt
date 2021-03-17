package com.example.latber.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.latber.adapters.Market_Items_Adapter
import com.example.latber.R
import com.example.latber.activities.Market_Item


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

        arrayList.add(Market_Item(R.drawable.pic1, "PS5", 1000000))
        arrayList.add(
            Market_Item(
                    R.drawable.pic2,
                "Pleys Tesien Lima",
                1000000
            )
        )
        arrayList.add(Market_Item(R.drawable.pic1, "Laptop", 3000000))
        arrayList.add(Market_Item(R.drawable.pic2, "Mouse Gaming", 100000))
        arrayList.add(Market_Item(R.drawable.pic4, "Keyboard", 150000))
        arrayList.add(Market_Item(R.drawable.pic3, "Headset", 15000))


        val adapter = Market_Items_Adapter(requireContext(),arrayList)

        sparePartGridView.adapter = adapter
    }
}