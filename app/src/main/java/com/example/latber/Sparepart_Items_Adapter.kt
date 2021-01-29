package com.example.latber

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.latihanbersama.Sparepart_Item

class Sparepart_Items_Adapter(var context: Context, var arrayList:ArrayList<Sparepart_Item>): BaseAdapter() {
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

        var sparepartItem : Sparepart_Item = arrayList.get(position)

        imgs.setImageResource(sparepartItem.imgs!!)
        details.text = sparepartItem.detail
        price.text = sparepartItem.price.toString()

        return view
    }
}