package com.example.androidprofessional.utils

import android.widget.ImageView
import com.example.androidprofessional.R
import com.squareup.picasso.Picasso

object PicassoImageLoader {
    fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String?) {
        Picasso.get()
            .load("https:$imageLink")
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_load_error_vector)
            .into(imageView)
    }
}