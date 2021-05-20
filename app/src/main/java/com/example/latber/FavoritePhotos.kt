package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latber.adapters.FavoritePhotosRecycleViewAdapter
import kotlinx.android.synthetic.main.activity_favorite_photos.*
import java.io.File

//disini ada changes
class FavoritePhotos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Favorite Photos"

        setContentView(R.layout.activity_favorite_photos)

        //menentukan path internal storage atau membuat folder Photos jika folder blm ad
        val path = File(this.filesDir, "Photos")
        //buat list untuk menamung file
        var favorites = mutableListOf<String>()

        //cek apakah path yg dituju tersedia / exist
        if (path.exists()) {
            //cek apakah ukuran list file > 0 atau apakah ada file yg tersimpan dalam path
            if (path.listFiles().size != 0){
                //lakukan perulangan untuk menambahkan file ke dalam list untuk ditampilkan nantinya.
                for(file in path.listFiles()) {
                    favorites.add(file.toString())
                }

                //buat adapter untuk menampilkan gambar nantinyaa
                val adapters = FavoritePhotosRecycleViewAdapter(favorites)
                favorite_rv.apply {
                    adapter = adapters
                    layoutManager = LinearLayoutManager(this@FavoritePhotos)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}