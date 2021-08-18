package com.deevvdd.politicalpreparedness.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.domain.model.response.Election

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */


@BindingAdapter("adapter")
fun bindAdapter(recyclerView: RecyclerView, adapter: ListAdapter<*, *>?) {
    recyclerView.adapter = adapter
}

@BindingAdapter("items")
fun bindAdapter(recyclerView: RecyclerView, items: List<Any>?) {
    (recyclerView.adapter as? ListAdapter<*, *>)?.submitList(items as List<Nothing>?)
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView,url :String?) {
    Glide.with(imageView.context).applyDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
    ).load(url).into(imageView)
}