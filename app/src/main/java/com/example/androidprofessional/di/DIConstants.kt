package com.example.androidprofessional.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

internal const val NAME_REMOTE = "Remote"
internal const val NAME_LOCAL = "Local"

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)