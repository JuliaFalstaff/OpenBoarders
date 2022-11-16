package com.example.module

import com.example.module.data.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class SuccessCard(val data: DataModel?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
