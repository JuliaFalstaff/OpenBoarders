package com.example.androidprofessional.view

import com.example.androidprofessional.model.AppState
import io.reactivex.Observable

class Contract {
    interface View {
        fun renderData(appState: AppState)
    }

    interface Presenter<T : AppState, V : View> {
        fun attachView(view: V)
        fun detachView(view: V)
        fun getData(word: String, isOnline: Boolean)
    }

    interface Interactor<T> {
        fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
    }

    interface Repository<T> {
        fun getData(word: String): Observable<T>
    }

    interface DataSource<T> {
        fun getData(word: String): Observable<T>
    }
}