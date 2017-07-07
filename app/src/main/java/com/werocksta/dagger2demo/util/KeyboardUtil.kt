package com.werocksta.dagger2demo.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class KeyboardUtil @Inject constructor(val context: Context, val inputMethodManager: InputMethodManager) {

    fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
