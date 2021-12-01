package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.retrofit.RetrofitImpl
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()): IDataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}