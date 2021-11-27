package com.example.androidprofessional.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class SchedulerProvider : ISchedulers {
    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}