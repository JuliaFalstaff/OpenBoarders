package com.example.androidprofessional.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprofessional.databinding.ItemTranslationLayoutBinding
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.utils.DiffUtils

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<DataModel>,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setData(newListData: List<DataModel>) {
        val callback = DiffUtils(data, newListData)
        val result = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(newListData)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTranslationLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding: ItemTranslationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextViewRecyclerItem.text = data.text
                binding.descriptionTextViewRecyclerItem.text = data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionTextView.text = "[${data.meanings?.first()?.transcription}]"
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}