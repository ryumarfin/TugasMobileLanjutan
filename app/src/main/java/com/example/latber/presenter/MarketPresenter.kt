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
        ItemsModel.add(Market_Item("https://asia.playstation.com/content/dam/pscom/hk/latest-news/2020/202009-ps5-news/ps5-prices.jpg", "Aaaa", 1000000))
        ItemsModel.add(Market_Item("https://media.suara.com/pictures/653x366/2020/09/01/75434-axioo-mypc-u23.jpg", "Bbbbb", 1000000))
        ItemsModel.add(Market_Item("https://images-na.ssl-images-amazon.com/images/I/71cngLX2xuL.__AC_SX300_SY300_QL70_FMwebp_.jpg", "Ccccc", 3000000))
        ItemsModel.add(Market_Item("https://www.pandaindoteknik.co.id/wp-content/uploads/2019/06/Spare-Parts-Cover.jpg", "Dddddd", 100000))
        ItemsModel.add(Market_Item("https://media.suara.com/pictures/653x366/2020/09/01/75434-axioo-mypc-u23.jpg", "Eeeeee", 150000))
        ItemsModel.add(Market_Item("https://asia.playstation.com/content/dam/pscom/hk/latest-news/2020/202009-ps5-news/ps5-prices.jpg", "Fffffff", 15000))

        //tampilkan data item ke dalam view dgn memanggil fungsi
        //ItemDetails yg ad pd MarketInterface dgn mengirimkan modelnya
        //(menghubungkan antara data dan tampilan)
        view.ItemDetails(ItemsModel)
    }
}