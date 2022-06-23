package com.tony.image

import android.widget.ImageView
import com.bumptech.glide.Glide



fun ImageView.loadUrl(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    Glide.with(context).load(url).into(this)
}
//other image load method