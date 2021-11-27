package com.example.androidprofessional.rx

import io.reactivex.Scheduler

interface ISchedulers {
    fun io(): Scheduler
    fun main() : Scheduler
}