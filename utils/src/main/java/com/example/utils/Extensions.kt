package com.example.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE,
) {
    Snackbar.make(this, text, length)
            .setAction(actionText, action)
            .show()
}