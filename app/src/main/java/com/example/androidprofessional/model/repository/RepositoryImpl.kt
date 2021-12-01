package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel
import io.reactivex.Observable

class RepositoryImpl(private val dataSource: IDataSource<List<DataModel>>) : IRepository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}