package com.deevvdd.politicalpreparedness.features.election

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.politicalpreparedness.databinding.ItemElectionBinding
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.utils.show

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */

interface ElectionAdapterCallback {
    fun onItemClick(item: Election)
    fun onDelete(item: Election)
}

class ElectionAdapter(
    private val callback: ElectionAdapterCallback,
    private val isSavedElection: Boolean = false
) :
    ListAdapter<Election, ElectionAdapter.ElectionVH>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionVH {
        return ElectionVH(
            ItemElectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: ElectionVH, position: Int) {
        holder.onBind(getItem(position))

    }

    inner class ElectionVH(val binding: ItemElectionBinding, callback: ElectionAdapterCallback) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(election: Election) {
            binding.vm = election
            if (isSavedElection)
                binding.ivDelete.show()
            binding.lyContainer.setOnClickListener { callback.onItemClick(election) }
            binding.ivDelete.setOnClickListener { callback.onDelete(election) }
        }
    }


    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<Election>() {
            override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
                return oldItem == newItem
            }
        }
    }


}