package com.deevvdd.politicalpreparedness.features.representative

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.politicalpreparedness.databinding.ItemRepresentativeBinding
import com.deevvdd.politicalpreparedness.domain.model.response.Channel
import com.deevvdd.politicalpreparedness.domain.model.response.Representative
import com.deevvdd.politicalpreparedness.utils.show
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
class RepresentativeAdapter : ListAdapter<Representative, RepresentativeAdapter.RepresentativeVH>(
    diffUtils
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeVH {
        return RepresentativeVH(
            ItemRepresentativeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepresentativeVH, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class RepresentativeVH(val binding: ItemRepresentativeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Representative) {
            binding.item = item
            item.official.channels?.let {
                setupSocialLinks(it)
            }
            item.official.urls?.let {
                openLink(it.first())
            }
        }


        private fun setupSocialLinks(channels: List<Channel>) {
            val fbUrl = validFacebookUrl(channels)
            val twitter = validFacebookUrl(channels)
            if (!fbUrl.isNullOrEmpty()) {
                binding.ivFacebook.show()
                binding.ivFacebook.setOnClickListener { openLink(fbUrl) }
            }
            if (!twitter.isNullOrEmpty()) {
                binding.ivTwitter.show()
                binding.ivTwitter.setOnClickListener { openLink(twitter) }
            }
        }

        private fun openLink(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            binding.root.context.startActivity(intent)
        }
    }

    private fun validFacebookUrl(channels: List<Channel>): String? {
        return channels.filter { channel -> channel.type == "Facebook" }
            .map { channel -> "https://www.facebook.com/${channel.id}" }
            .firstOrNull()
    }

    private fun validTwitterUrl(channels: List<Channel>): String? {
        return channels.filter { channel -> channel.type == "Twitter" }
            .map { channel -> "https://www.twitter.com/${channel.id}" }
            .firstOrNull()
    }


    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<Representative>() {
            override fun areItemsTheSame(
                oldItem: Representative,
                newItem: Representative
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Representative,
                newItem: Representative
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}