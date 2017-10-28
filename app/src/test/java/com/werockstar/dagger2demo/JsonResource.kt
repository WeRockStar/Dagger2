package com.werockstar.dagger2demo

import okio.Okio

object JsonResource {
    @Throws(Exception::class)
    fun fromResource(file: String): String {
        val classLoader = Thread.currentThread().contextClassLoader
        classLoader.getResourceAsStream(file).use { `is` -> return Okio.buffer(Okio.source(`is`)).readUtf8() }
    }
}
