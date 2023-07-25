package com.example.myapplication.utils

import android.app.Application
import com.example.myapplication.utils.interactors.GlideInteractor

object Interactors {
    lateinit var glide: GlideInteractor
    var context: Application? = null
}