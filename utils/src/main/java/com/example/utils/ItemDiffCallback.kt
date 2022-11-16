package com.example.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.module.data.DataModel

class ItemDiffCallback: DiffUtil.ItemCallback<DataModel>(){
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }
}