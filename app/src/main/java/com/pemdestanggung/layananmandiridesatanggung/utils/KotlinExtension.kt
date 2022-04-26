package com.pemdestanggung.layananmandiridesatanggung.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pemdestanggung.layananmandiridesatanggung.R

fun ImageView.loadUrl(baseUrl: String, value: String) {
    Glide.with(this).clear(this)
    Glide.with(this).load(baseUrl + value).centerCrop()
        .placeholder(R.drawable.ic_logo_kabupaten_malang).into(this)
}