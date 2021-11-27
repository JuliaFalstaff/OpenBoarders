package com.example.androidprofessional.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class SchedulerProvider : Schedulers {
    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}