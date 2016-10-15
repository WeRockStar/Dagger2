package com.werocksta.dagger2demo;

import java.io.InputStream;

import okio.Okio;

public class JsonResource {
    @SuppressWarnings("NewApi")
    public static String fromResource(String file) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(file)) {
            return Okio.buffer(Okio.source(is)).readUtf8();
        }
    }
}
