package com.example.favouritescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favouritescreen.databinding.ItemFavouriteRecyclerBinding
import com.example.module.data.DataModel

class FavouriteAdapter(private var data: MutableList<DataModel>) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    fun setData(data: MutableList<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavouriteRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding: ItemFavouriteRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.wordTextView.text = data.text
                binding.descriptionWordTextView.text =
                    data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionTextView.text =
                    data.meanings?.first()?.transcription.toString()
            }
        }
    }
}