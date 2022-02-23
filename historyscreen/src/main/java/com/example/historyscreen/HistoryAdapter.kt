package com.example.historyscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.historyscreen.databinding.ItemHistoryRecyclerViewBinding
import com.example.module.data.DataModel

class HistoryAdapter(private var data: List<DataModel>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRecyclerViewBinding.inflate(
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

    inner class ViewHolder(val binding: ItemHistoryRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextViewRecyclerItem.text = data.text
                binding.descriptionHistoryTextViewRecyclerItem.text =
                    data.meanings?.joinToString { it.translation?.translation.toString() }
            }
        }
    }
}