package com.example.androidprofessional.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprofessional.databinding.ItemTranslationLayoutBinding
import com.example.androidprofessional.model.data.DataModel

class MainAdapter(
        private var onListItemClickListener: OnListItemClickListener,
        private var data: List<DataModel>,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTranslationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding: ItemTranslationLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextViewRecyclerItem.text = data.text
                binding.descriptionTextViewRecyclerItem.text = data.meanings?.first()?.translation?.translation
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