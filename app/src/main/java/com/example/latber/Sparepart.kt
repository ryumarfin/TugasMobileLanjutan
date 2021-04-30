package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latber.adapters.Market_Items_Adapter
import com.example.latber.data.Market_Item

class Sparepart : AppCompatActivity() {
//    , AdapterView.OnItemClickListener
    private var arrayList: ArrayList<Market_Item>? = null
    private var sparepartItemsAdapter: Market_Items_Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        arrayList = ArrayList()
        arrayList = setDataList()

        sparepartItemsAdapter = Market_Items_Adapter(applicationContext, arrayList!!)
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

//    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        var sparepartItem: Market_Item = arrayList!!.get(position)
//
//        var intentItem = Intent(this, beli::class.java)
//
//        intentItem.putExtra("resId", sparepartItem.imgs)
//
//        intentItem.putExtra(EXTRA_KETERANGAN, sparepartItem.detail.toString())
//        intentItem.putExtra(EXTRA_HARGA, sparepartItem.price.toString())
//
//        startActivity(intentItem)
//    }
}