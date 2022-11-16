package com.example.androidprofessional.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprofessional.databinding.ItemHistoryRecyclerViewBinding
import com.example.module.data.DataModel
import com.example.utils.ItemDiffCallback

class HistoryAdapter(
): ListAdapter<DataModel, HistoryAdapter.ViewHolder>(ItemDiffCallback()) {

    var onItemClick: ((DataModel)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemHistoryRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextViewRecyclerItem.text = data.text
                binding.descriptionHistoryTextViewRecyclerItem.text =
                    data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionHistoryTextView.text =
                    "[${data.meanings?.first()?.transcription}]"
                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}