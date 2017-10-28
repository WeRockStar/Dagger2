package com.werockstar.dagger2demo.util

import android.view.View
import android.view.inputmethod.InputMethodManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class KeyboardUtilTest {

    @Mock lateinit var view: View
    @Mock lateinit var inputMethodManager: InputMethodManager
    lateinit var keyboard: KeyboardUtil

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        keyboard = KeyboardUtil(inputMethodManager)
    }

    @Test
    fun keyboard_should_hide() {
        Mockito.`when`(inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS))
                .thenReturn(true)

        keyboard.hideKeyboard(view)

        Mockito.verify(inputMethodManager).hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}