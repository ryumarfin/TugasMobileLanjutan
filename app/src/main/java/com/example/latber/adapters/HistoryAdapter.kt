package com.example.latber.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.latber.R
import com.example.latber.Riwayat

class historyAdapter(data: MutableList<Riwayat>): RecyclerView.Adapter<historyAdapter.myHolder>() {
    private var myData = data
    class myHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Layanan = itemView.findViewById<TextView>(R.id.tv_layanan)
        val harga = itemView.findViewById<TextView>(R.id.tv_hargaht)
        val keperluan = itemView.findViewById<TextView>(R.id.tv_keperluan)
        val tanggal = itemView.findViewById<TextView>(R.id.tv_tanggalht)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.myhistory_view, parent, false)
            return myHolder(inflate)
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.Layanan.setText(myData.get(position).Layanan)
        holder.harga.setText(myData.get(position).harga)

        holder.keperluan.setText(myData.get(position).Keperluan)

    }

    override fun getItemCount(): Int = myData.size
}