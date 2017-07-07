package com.werocksta.dagger2demo.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class KeyboardUtilTest {

    @Mock lateinit var context: Context
    @Mock lateinit var view: View
    @Mock lateinit var inputMethodManager: InputMethodManager
    lateinit var keyboard: KeyboardUtil

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        keyboard = KeyboardUtil(context, inputMethodManager)
    }

    @Test
    fun keyboard_should_hide() {
        keyboard.hideKeyboard(view)
        Mockito.verify(inputMethodManager).hideSoftInputFromInputMethod(Mockito.any(), Mockito.any())
    }
}