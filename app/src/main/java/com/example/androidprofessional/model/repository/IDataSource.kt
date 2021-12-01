package com.example.androidprofessional.model.repository

import io.reactivex.Observable

interface IDataSource<T> {
    fun getData(word: String): Observable<T>
}