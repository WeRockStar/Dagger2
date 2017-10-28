package com.werockstar.dagger2demo.rx

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named


class RxThread @Inject constructor(@Named("mainThread") val mainSchedulers: Scheduler,
                                   @Named("ioThread") val ioScheduler: Scheduler) {

    fun <T> applyAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(ioScheduler)
                    .observeOn(mainSchedulers)
        }
    }
}