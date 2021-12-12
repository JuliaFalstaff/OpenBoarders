package com.example.androidprofessional.utils

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.room.entity.FavouriteEntity
import com.example.androidprofessional.model.room.entity.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): MutableList<DataModel> {
    val dataModel = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(DataModel(entity.id, entity.word, entity.meanings))
        }
    }
    return dataModel
}

fun convertFromEntityToData(entity: HistoryEntity): List<DataModel> {
    return listOf(
        DataModel(id = entity.id, text = entity.word, meanings = entity.meanings)
    )
}

fun convertFromDataToEntity(word: DataModel) =
    HistoryEntity(
        id = word.id, word = word.text, meanings = word.meanings
    )

fun convertFromDataToFavEntity(word: DataModel) =
    FavouriteEntity(
        id = word.id, word = word.text, meanings = word.meanings
    )

fun convertFromFavEntityToData(entity: FavouriteEntity): List<DataModel> {
    return listOf(
        DataModel(id = entity.id, text = entity.word, meanings = entity.meanings)
    )
}