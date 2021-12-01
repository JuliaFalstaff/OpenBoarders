package com.example.androidprofessional.model.repository

import io.reactivex.Observable

interface IRepository<T> {
    fun getData(word: String): Observable<T>
}