package com.example.androidprofessional.utils

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler
    fun main() : Scheduler
}