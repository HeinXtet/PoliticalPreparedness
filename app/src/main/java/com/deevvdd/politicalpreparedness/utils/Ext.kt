package com.deevvdd.politicalpreparedness.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deevvdd.politicalpreparedness.R

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */


fun View.show() {
    this.isVisible = true
}


fun View.hide() {
    this.isVisible = false
}

fun ImageView.load(url: String) {
    Glide.with(this.context).applyDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
    ).load(url).into(this)
}

