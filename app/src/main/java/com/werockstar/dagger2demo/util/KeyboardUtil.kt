package com.werockstar.dagger2demo.util

import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class KeyboardUtil @Inject constructor(private val inputMethodManager: InputMethodManager) {

    fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
