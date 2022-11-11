package com.example.androidprofessional.favourite

import com.example.module.data.DataModel

interface IOnListItemClickListener  {
    fun onItemClick(data: DataModel)
    fun onItemDelete(data: DataModel)
}