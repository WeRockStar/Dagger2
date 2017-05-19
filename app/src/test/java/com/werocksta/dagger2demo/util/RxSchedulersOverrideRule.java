package com.werocksta.dagger2demo.util;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class RxSchedulersOverrideRule implements TestRule {

    private Function<Callable<Scheduler>, Scheduler> mRxJavaSchedulersHook = new Function<Callable<Scheduler>, Scheduler>() {
        @Override
        public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
            return Schedulers.trampoline();
        }
    };

    private Function<Scheduler, Scheduler> mSchedulerFunction = new Function<Scheduler, Scheduler>() {
        @Override
        public Scheduler apply(Scheduler scheduler) throws Exception {
            return Schedulers.trampoline();
        }
    };

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(mRxJavaSchedulersHook);

                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(mSchedulerFunction);
                RxJavaPlugins.setNewThreadSchedulerHandler(mSchedulerFunction);
                RxJavaPlugins.setComputationSchedulerHandler(mSchedulerFunction);

                base.evaluate();

                RxAndroidPlugins.reset();
                RxJavaPlugins.reset();
            }
        };
    }
}
