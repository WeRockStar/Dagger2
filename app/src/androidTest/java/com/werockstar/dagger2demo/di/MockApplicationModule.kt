package com.werockstar.dagger2demo.di


import android.app.Application
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
class MockApplicationModule(val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Application = application

    @Provides
    @Singleton
    fun provideInputMethod() = mock(InputMethodManager::class.java)


}
