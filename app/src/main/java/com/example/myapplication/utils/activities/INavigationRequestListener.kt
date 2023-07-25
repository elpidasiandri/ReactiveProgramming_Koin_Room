package com.example.myapplication.utils.activities

import android.content.Intent
import androidx.fragment.app.Fragment

internal interface INavigationRequestListener {

    fun onStartActivity(intent: Intent)

    fun onGoBack()

    fun showDialogFragment(dialogFragment: BaseDialogFragment)
}