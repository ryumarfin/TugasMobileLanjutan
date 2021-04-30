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
        ItemsModel.add(Market_Item(R.drawable.pic1, "PS5", 1000000))
        ItemsModel.add(Market_Item(R.drawable.pic2, "Pleys Tesien Lima", 1000000))
        ItemsModel.add(Market_Item(R.drawable.pic1, "Laptop no. 1", 3000000))
        ItemsModel.add(Market_Item(R.drawable.pic2, "Mouse Gaming", 100000))
        ItemsModel.add(Market_Item(R.drawable.pic4, "Keyboard", 150000))
        ItemsModel.add(Market_Item(R.drawable.pic3, "Headset", 15000))

        //tampilkan data item ke dalam view dgn memanggil fungsi
        //ItemDetails yg ad pd MarketInterface dgn mengirimkan modelnya
        //(menghubungkan antara data dan tampilan)
        view.ItemDetails(ItemsModel)
    }
}