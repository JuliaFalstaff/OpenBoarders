package com.example.androidprofessional.model.room

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.view.Contract
import io.reactivex.Observable

class RoomDataBaseImplementation : Contract.DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }

}