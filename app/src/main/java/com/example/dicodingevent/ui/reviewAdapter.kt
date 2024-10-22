package com.example.dicodingevent.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.databinding.EventListBinding

class reviewAdapter: ListAdapter<ListEventsItem, reviewAdapter.EventViewHolder>(DIFF_CALLBACK) {
    private val events: MutableList<ListEventsItem> = mutableListOf()

    inner class EventViewHolder(private val binding: EventListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: ListEventsItem) {
            binding.tvName.text = event.name
            Glide.with(binding.root.context).load(event.imageLogo).into(binding.ivThumbnail)
            binding.tvOwner.text = event.ownerName
            binding.tvLocation.text = event.cityName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): reviewAdapter.EventViewHolder {
        val binding = EventListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

//    class MyViewHolder(val binding: EventListBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(listEvent: ListEventsItem){
//            binding.tvItem.text = "${listEvent.name}\n- ${listEvent.description}"
//        }
//    }

    override fun getItemCount(): Int = events.size

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}