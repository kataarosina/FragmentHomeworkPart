package com.example.fragmenthomework

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fragmenthomework.databinding.ItemContactBinding
import java.net.URL


class ContactAdapter(
    context: Context,
    private val onItemClicked: (Contact) -> Unit,
    private val onItemLongClicked: (Contact) -> Unit
) : ListAdapter<Contact, ContactViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked, onItemLongClicked)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class ContactViewHolder(
    private val binding: ItemContactBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Contact,
        onItemClick: (Contact) -> Unit,
        onItemLongClick: (Contact) -> Unit
    ) {
        binding.textViewFirstName.text = item.firstName
        binding.textViewLastName.text = item.lastName
        binding.textViewNumber.text = item.number
        val imageUrl = "https://loremflickr.com/320/240/${item.id}"
        binding.imageView.load(imageUrl)
        itemView.setOnClickListener { onItemClick(item) }
        itemView.setOnLongClickListener {
            onItemLongClick(item)
            true
        }
    }
}