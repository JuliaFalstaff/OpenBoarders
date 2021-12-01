package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.room.RoomDataBaseImplementation
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()): IDataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}