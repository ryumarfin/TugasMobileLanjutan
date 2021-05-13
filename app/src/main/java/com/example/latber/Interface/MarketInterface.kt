package com.example.latber.Interface

import com.example.latber.data.Market_Item

//buat sebuah Interface utk menghubungkn tampilan dgn presenter & datanya
interface MarketInterface {
    //fungsi utk menampilkan data yg dikirim model ke view
    fun ItemDetails(ItemModel : ArrayList<Market_Item>)
}