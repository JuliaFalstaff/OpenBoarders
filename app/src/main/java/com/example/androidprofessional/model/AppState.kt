package com.example.androidprofessional.model

import com.example.androidprofessional.model.data.DataModel

sealed class AppState {
    data class Success(val data: MutableList<DataModel>?) : AppState()
    data class SuccessHistoryData(val history: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
