package com.gtplanner.ui.viewAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("Android:imageUrl")
fun setImageURL(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}