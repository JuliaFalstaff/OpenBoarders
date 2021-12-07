package com.example.androidprofessional.model.room

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IDataSource
import io.reactivex.Observable

class RoomDataBaseImpl: IDataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}