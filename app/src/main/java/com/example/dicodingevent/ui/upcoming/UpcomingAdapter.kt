package com.example.dicodingevent.ui.upcoming

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.dicodingevent.BaseAdapter
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.databinding.EventListBinding

class UpcomingAdapter(override val DIFF_CALLBACK: DiffUtil.ItemCallback<ListEventsItem>) : BaseAdapter<ListEventsItem>(DIFF_CALLBACK) {
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun bind(binding: EventListBinding, item: ListEventsItem) {
        binding.tvName.text = item.name
        binding.tvOwner.text = item.ownerName
        binding.tvLocation.text = item.cityName
        Glide.with(binding.root.context).load(item.imageLogo).into(binding.ivThumbnail)

        binding.root.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
}