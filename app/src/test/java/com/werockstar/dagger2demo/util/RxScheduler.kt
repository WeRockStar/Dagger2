package com.werockstar.dagger2demo.util

import com.werockstar.dagger2demo.rx.RxThread
import io.reactivex.schedulers.Schedulers

object RxScheduler {
    val rxScheduler = RxThread(Schedulers.trampoline(), Schedulers.trampoline())
}