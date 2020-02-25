package com.werockstar.dagger2demo

import okio.Okio

fun String.toJsonString(): String {
	val classLoader = Thread.currentThread().contextClassLoader
	return classLoader.getResourceAsStream(this).use { `is` -> return Okio.buffer(Okio.source(`is`)).readUtf8() }
}
