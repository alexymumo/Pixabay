package com.example.pixabay.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.pixabay.data.local.entity.Pixabay

class PixabayComparator: DiffUtil.ItemCallback<Pixabay>() {
    override fun areItemsTheSame(oldItem: Pixabay, newItem: Pixabay): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Pixabay, newItem: Pixabay): Boolean {
        return oldItem == newItem
    }
}