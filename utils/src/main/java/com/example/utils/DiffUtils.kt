package com.example.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.module.data.DataModel

class DiffUtils(
    private val oldList: List<DataModel>,
    private val newList: List<DataModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].text == newList[newItemPosition].text
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}