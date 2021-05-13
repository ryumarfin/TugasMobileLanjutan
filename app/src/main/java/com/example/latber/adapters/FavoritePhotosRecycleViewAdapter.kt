package com.example.latber.adapters

import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.latber.R
import kotlinx.android.synthetic.main.favorite_photo_recycleview_item.view.*

class FavoritePhotosRecycleViewAdapter(val photos: List<String>) : RecyclerView.Adapter<FavoritePhotoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePhotoHolder {
        return FavoritePhotoHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_photo_recycleview_item,parent,false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: FavoritePhotoHolder, position: Int) {
        holder.photo.setImageDrawable(Drawable.createFromPath(photos[position]))
    }
}

class FavoritePhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
    var photo : ImageView

    init {
        photo = view.favorite_photo
    }
}