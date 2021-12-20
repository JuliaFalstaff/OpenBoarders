package com.example.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class OnlineLiveData(contex: Context): LiveData<Boolean>() {
    private val availableNetworks = mutableSetOf<Network>()
    private val connectivityManager = contex.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val request: NetworkRequest = NetworkRequest.Builder().build()

    // Если соединение потеряно/восстановлено, убираем/добавляем его из/в массива и уведомляем  подписчиков о наличии связи
    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            update(availableNetworks.isNotEmpty())
        }

        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            update(availableNetworks.isNotEmpty())
        }
    }

    private fun update(isOnline: Boolean) {
        if (isOnline!= value) {
            postValue(isOnline)
        }
    }

    // Регистрируем колбэк, если компонент, подписанный на LiveData, активен
    override fun onActive() {
        connectivityManager.registerNetworkCallback(request, callback)
    }
    // Убираем колбэк, если компонент, подписанный на LiveData, неактивен
    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}