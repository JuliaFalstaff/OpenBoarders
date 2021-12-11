package com.example.androidprofessional.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.androidprofessional.model.data.DataModel

class DiffUtils(
    private val oldList: List<DataModel>,
    private val newList: List<DataModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text.equals(newList[newItemPosition].text)
                && oldList[oldItemPosition].id?.equals(newList[newItemPosition].id) == true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}