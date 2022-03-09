package com.example.pixabay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.databinding.ImageItemBinding
import com.example.pixabay.utils.PixabayComparator


class PixabayAdapter: ListAdapter<Pixabay,PixabayAdapter.PixabayViewHolder>(PixabayComparator()) {

    inner class PixabayViewHolder(val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root)

    fun bind(pixabay: Pixabay) {
    }


    //val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        val imageItemBinding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PixabayViewHolder(imageItemBinding)
    }

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        val pixabay = currentList[position]
        holder.itemView.setOnClickListener {
        }

    }
    override fun getItemCount() = currentList.size
}
