package com.example.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(
        private val root: () -> View?,
        private val viewId: Int,
) {
    private var rootReference: WeakReference<View>? = null
    private var viewRef: T? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootReference?.get()
        val currentRoot = root()
        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    return view
                }
                throw IllegalStateException(EXCEPTION_MSG)
            }
            view = currentRoot.findViewById(viewId)
            viewRef = view
            rootReference = WeakReference(currentRoot)
        }
        checkNotNull(view) { "view with id $viewId not found" }
        return view
    }

    companion object {
        private const val EXCEPTION_MSG = "Can't get root"
    }
}

fun <T : View> Fragment.fragmentViewById(@IdRes id: Int): ViewByIdDelegate<T> =
        ViewByIdDelegate({ view }, id)

fun <T : View> Activity.activityViewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}


