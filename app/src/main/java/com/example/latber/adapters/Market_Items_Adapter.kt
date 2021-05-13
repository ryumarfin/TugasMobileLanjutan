package com.example.latber.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.latber.DETAIL_ITEM
import com.example.latber.R
import com.example.latber.activities.beli
import com.example.latber.data.Market_Item


class Market_Items_Adapter(var context: Context, var arrayList:ArrayList<Market_Item>): BaseAdapter() {
    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View = View.inflate(context, R.layout.gridview_item, null)

        var imgs: ImageView = view.findViewById(R.id.img1)
        var details: TextView = view.findViewById(R.id.tv1)
        var price: TextView = view.findViewById(R.id.tv2)

        var sparepartItem : Market_Item = arrayList.get(position)


//        val urlNew = GlideUrl(
//            sparepartItem.imgs!!, LazyHeaders.Builder()
//                .addHeader("User-Agent", "your-user-agent")
//                .build()
//        )
        Glide.with(context).load(sparepartItem.imgs!!).into(imgs)
        details.text = sparepartItem.detail
        price.text = sparepartItem.price.toString()

        view.findViewById<LinearLayout>(R.id.item_sparepare).setOnClickListener {
            // membuat intent eksplisit untuk dapat mengirimkan data ke actiity beli
            var intentDetail = Intent(context, beli::class.java)
            // membentuk objek parcelable dari view yang di click agar dapat dikirimkan ke activity beli
            var item = Market_Item(arrayList[position].imgs,arrayList[position].detail,arrayList[position].price)
            // memasukkan parcelable tadi ke dalam EXTRA/key
            intentDetail.putExtra(DETAIL_ITEM,item)
            // mulai activity
            context.startActivity(intentDetail)
        }

        return view
    }
}