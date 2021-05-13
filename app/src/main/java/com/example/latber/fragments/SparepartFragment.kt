package com.example.latber.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.latber.Interface.MarketInterface
import com.example.latber.R
import com.example.latber.adapters.Market_Items_Adapter
import com.example.latber.data.Market_Item
import com.example.latber.presenter.MarketPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SparepartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//implementasi MarketInterface untuk menghubungkan antara presenter & view
class SparepartFragment : Fragment(), MarketInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view ny
        return inflater.inflate(R.layout.fragment_sparepart_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //kirimkan view nya
        var presenter = MarketPresenter(this)

        //panggil fungsi ItemList yg telah di buat ada MarketPresenter
        presenter.ItemList()





        //ganti jd pakai MVP saja

//        val sparePartGridView = view.findViewById<GridView>(R.id.myGridView)
//
//        //ok
//        var arrayList: ArrayList<Market_Item> = ArrayList()
//
//        arrayList.add(Market_Item(R.drawable.pic1, "PS5", 1000000))
//        arrayList.add(Market_Item(R.drawable.pic2, "Pleys Tesien Lima", 1000000))
//        arrayList.add(Market_Item(R.drawable.pic1, "Laptop no. 1", 3000000))
//        arrayList.add(Market_Item(R.drawable.pic2, "Mouse Gaming", 100000))
//        arrayList.add(Market_Item(R.drawable.pic4, "Keyboard", 150000))
//        arrayList.add(Market_Item(R.drawable.pic3, "Headset", 15000))
//
//        val adapter = Market_Items_Adapter(requireContext(),arrayList)
//        sparePartGridView.adapter = adapter
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

    //fungsi utk menampilkan hasil ke dlm view
    override fun ItemDetails(ItemModel: ArrayList<Market_Item>) {
        //kirimkan modelnya ke adapter
        val sparePartGridView = view!!.findViewById<GridView>(R.id.myGridView)
        val adapter = Market_Items_Adapter(requireContext(), ItemModel)
        sparePartGridView.adapter = adapter
    }
}