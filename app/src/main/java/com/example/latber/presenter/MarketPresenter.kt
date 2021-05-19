package com.example.latber.presenter

import com.example.latber.Interface.MarketInterface
import com.example.latber.R
import com.example.latber.data.Market_Item

//buat sebuah param setView agar MarketPresenter hanya
// dapat diakses oleh yg mengimplementasikan MarketInterface
class MarketPresenter(setView:MarketInterface) {
    //simpan interface/view nya
    private var view = setView
    //var model utk menyimpan data item nya
    private var ItemsModel = arrayListOf<Market_Item>()

    //proses data
    fun ItemList(){
        //isi data
        ItemsModel.add(Market_Item("https://img.cintamobil.com/2019/11/07/ZXn5Pos1/tertipu-beli-sparepart-mobil-4-9a07.jpg", "Aaaa", 1000000))
        ItemsModel.add(Market_Item("https://jual-sparepart-motor.com/wp-content/uploads/2014/05/BEARING-LAHAR-1-260x280.jpg", "Bbbbb", 1000000))
        ItemsModel.add(Market_Item("https://momobil.id/news/wp-content/uploads/2018/12/mencari-toko-spare-part-mobil-terbaik-1024x576.jpg", "Ccccc", 3000000))
        ItemsModel.add(Market_Item("https://www.pandaindoteknik.co.id/wp-content/uploads/2019/06/Spare-Parts-Cover.jpg", "Dddddd", 100000))
        ItemsModel.add(Market_Item("https://5.imimg.com/data5/QD/DV/MY-17920646/perfect-gen-set-eicher-engine-250x250.jpg", "Eeeeee", 150000))
        ItemsModel.add(Market_Item("https://www.thecronutproject.com/wp-content/uploads/2021/01/Sparepart-motor.jpg", "Fffffff", 15000))

        //tampilkan data item ke dalam view dgn memanggil fungsi
        //ItemDetails yg ad pd MarketInterface dgn mengirimkan modelnya
        //(menghubungkan antara data dan tampilan)
        view.ItemDetails(ItemsModel)
    }
}