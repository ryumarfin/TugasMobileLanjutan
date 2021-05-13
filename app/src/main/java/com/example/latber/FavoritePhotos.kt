package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latber.adapters.FavoritePhotosRecycleViewAdapter
import kotlinx.android.synthetic.main.activity_favorite_photos.*
import java.io.File

class FavoritePhotos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Favorite Photos"

        setContentView(R.layout.activity_favorite_photos)

        val path = File(this.filesDir, "Photos")
        var favorites = mutableListOf<String>()

        if (path.exists()) {
            if (path.listFiles().size != 0){
                for(file in path.listFiles()) {
                    favorites.add(file.toString())
                }

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