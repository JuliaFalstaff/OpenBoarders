//package com.example.androidprofessional.model.repository
//
//import com.example.androidprofessional.model.data.DataModel
//import com.example.androidprofessional.model.retrofit.RetrofitImpl
//
//class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
//    IDataSource<List<DataModel>> {
//    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
//}