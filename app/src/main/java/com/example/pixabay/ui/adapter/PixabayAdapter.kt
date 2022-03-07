package com.example.pixabay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.pixabay.data.local.entity.Pixabay
import com.example.pixabay.databinding.ImageItemBinding

class PixabayAdapter: RecyclerView.Adapter<PixabayAdapter.PixabayViewHolder>() {

    inner class PixabayViewHolder(val binding: ImageItemBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Pixabay>() {
        override fun areItemsTheSame(oldItem: Pixabay, newItem: Pixabay) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pixabay, newItem: Pixabay) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        val imageItemBinding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PixabayViewHolder(imageItemBinding)
    }

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        val pixabay = differ.currentList[position]
        val imageUrl = pixabay.webformatURL
      //  val user = pixabay.user
        holder.binding.apply {
            imageView.load(imageUrl){
                transformations(RoundedCornersTransformation(10f))
                crossfade(true)
            }
        }
    }
    override fun getItemCount() = differ.currentList.size
}