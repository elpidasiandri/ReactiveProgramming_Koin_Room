package com.example.myapplication.utils.activities

import android.os.Bundle
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment : DialogFragment() {

    protected val orCreateArguments: Bundle
        get() {
            var args: Bundle? = arguments
            if (args == null) {
                args = Bundle()
                arguments = args
            }
            return args
        }
}