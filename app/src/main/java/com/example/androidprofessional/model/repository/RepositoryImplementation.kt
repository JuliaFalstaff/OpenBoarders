package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.view.Contract
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: Contract.DataSource<List<DataModel>>) : Contract.Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}