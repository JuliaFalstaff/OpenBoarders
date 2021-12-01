package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.room.RoomDataBaseImplementation
import com.example.androidprofessional.view.Contract
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) : Contract.DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}