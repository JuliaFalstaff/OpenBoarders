package com.example.historyscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.historyscreen.databinding.ItemHistoryRecyclerViewBinding
import com.example.module.data.DataModel
import com.example.utils.DiffUtils

class HistoryAdapter(private var data: List<DataModel>, private var listener: IOnListItemClickListener) :
        RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun setData(newListData: List<DataModel>) {
        val callback = DiffUtils(data, newListData)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        this.data = newListData
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
        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextViewRecyclerItem.text = data.text
                binding.descriptionHistoryTextViewRecyclerItem.text =
                        data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionHistoryTextView.text = "[${data.meanings?.first()?.transcription}]"
                itemView.setOnClickListener {
                    listener.onItemClick(data)
                }
            }
        }
    }
}