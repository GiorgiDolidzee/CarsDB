package com.example.homework16.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework16.R

fun ImageView.setImage(url: String?) {
    if(!url.isNullOrEmpty()) {
        Glide.with(context).load(url).placeholder(R.drawable.item_shape).error(R.drawable.item_shape).into(this)
    } else {
        setImageResource(R.drawable.item_shape)
    }
}